import { IsNull, Not } from "typeorm";
import { AppDataSource } from "../data-source.js";
import HttpException from "../http-exception.js";
import TaskToAdd from "../models/task-to-add.js";
import Task from "../models/task.entity.js";

interface ITaskWithTime extends Task {
  duration: string;
}

interface IHierarchy {
  [key: string]: IHierarchyCategoryEntry;
}

interface IHierarchyCategoryEntry {
  [key: string]: IHierarchyNameEntry;
}

interface IHierarchyNameEntry {
  tasks: Task[];
}

interface ITaskToAddList {
  [key: string]: string[]
}

const repository = AppDataSource.getRepository(Task);

export async function getTasksToAdd(){
  const tasks = await AppDataSource.manager.find(TaskToAdd);
  const hierarchy = {} as ITaskToAddList;
  tasks.forEach(t => {
    if (!(t.category in hierarchy)) {
      hierarchy[t.category] = [];
    }
    hierarchy[t.category].push(t.name);
  });
  return hierarchy;
}

export async function createTask(category: string, name: string, userId: number) {
  const task = new Task();
  repository.merge(task, { category, name, userId });

  await repository.save(task);
  return task;
}

export async function listRunningTasksForUser(userId: number) {
  const tasks = await repository.findBy({ userId, endTime: IsNull() });
  return tasks;
}

export async function changeToDone(taskId: number) {
  const task = await repository.findOneBy({ id: taskId }) as ITaskWithTime;
  if(!task) throw new HttpException(404, "Task Not Found")
  task.endTime = new Date();
  task.duration = formatTimespan(task.endTime!.getTime() - task.createdAt.getTime());
  await repository.save(task);
  return task;
}

export async function getDoneTasksFromAllUsers() {
  const tasks = await repository.find({
    where: { endTime: Not(IsNull()) },
    relations: [ 'user' ]
  }) as ITaskWithTime[];
  tasks.forEach(t => {
    t.duration = formatTimespan(t.endTime!.getTime() - t.createdAt.getTime())
  })
  return tasks;
}

export async function getDoneTasksFromCurrentUser(userId: number) {
  const tasks = await repository.findBy({ userId, endTime: Not(IsNull()) }) as ITaskWithTime[];
  tasks.forEach(t => {
    t.duration = formatTimespan(t.endTime!.getTime() - t.createdAt.getTime())
  })
  return tasks;
}

export async function getDoneTasksFromCurrentUserHierachy(userId: number) {
  const tasks = await repository.findBy({ userId, endTime: Not(IsNull()) }) as ITaskWithTime[];

  const hierarchy = taskListToHierarchy(tasks);

  return hierarchy;
}

export async function getDoneTasksFromAllUserHierarchy() {
  const tasks = await repository.find({
    where: { endTime: Not(IsNull()) },
    relations: [ 'user' ]
  }) as ITaskWithTime[];

  const hierarchy = taskListToHierarchy(tasks);

  return hierarchy;
}

function taskListToHierarchy(tasks: ITaskWithTime[]) {
  const hierarchy = {} as IHierarchy;
  tasks.forEach(t => {
    t.duration = formatTimespan(t.endTime!.getTime() - t.createdAt.getTime());
    if (!(t.category in hierarchy)) {
      hierarchy[t.category] = {};
    }
    if (!(t.name in hierarchy[t.category])) {
      hierarchy[t.category][t.name] = { tasks: [] };
    }
    hierarchy[t.category][t.name].tasks.push(t);
  });
  return hierarchy;
}

export async function getAllDoneFromOneTask(category: string, name: string) {
  const tasks = await repository.find({ 
    where: { category , name, endTime: Not(IsNull()) },
    relations: [ 'user' ]
  }) as ITaskWithTime[];
  tasks.forEach(t => {
    t.duration = formatTimespan(t.endTime!.getTime() - t.createdAt.getTime())
  })
  return tasks;
}

export async function getMeanFromTask(category: string, name: string) {
  const tasks = await repository.find({ 
    where: { category , name, endTime: Not(IsNull()) },
    relations: [ 'user' ]
  });

  const timeSum = tasks.reduce((acc, task) => {
    return acc + (task.endTime!.getTime() - task.createdAt.getTime());
  }, 0);

  const mean = timeSum / tasks.length;
  return formatTimespan(mean);
}

export async function getBestTimeEnded(category: string, name: string) {
  const tasks = await repository.find({ 
    where: { category , name, endTime: Not(IsNull()) },
    relations: [ 'user' ]
  });

  const times = tasks.map(t => t.endTime.getTime() - t.createdAt.getTime());
  console.log(times);
  return formatTimespan(Math.min(...times));
}

function formatTimespan(time: number) {
  if (time < 1000) {
    return "0s";
  }
  let sec = Math.floor(time / 1000);
  if (sec < 60) {
    return `${sec}s`;
  }
  let min = Math.floor(sec / 60);
  sec %= 60;
  if ( min < 60) {
    return `${min}m, ${sec}s`;
  }
  let hours = Math.floor(min / 60);
  min %= 60;
  if (hours < 24) { 
    return `${hours}h, ${min}m, ${sec}s`;
  }
  let days = Math.floor(hours / 24);
  hours %= 24;
  return `${days}d, ${hours}h, ${min}m, ${sec}s`
}
