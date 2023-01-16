import { IsNull, Not } from "typeorm";
import { AppDataSource } from "../data-source.js";
import Task from "../models/task.entity.js";

const repository = AppDataSource.getRepository(Task);

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
  const task = await repository.findOneBy({ id: taskId });
  task.endTime = new Date();
  await repository.save(task);
  return task;
}

export async function getDoneTasksFromAllUsers() {
  const tasks = await repository.find({
    where: { endTime: Not(IsNull()) },
    relations: [ 'user' ]
  });
  return tasks;
}

export async function getDoneTasksFromCurrentUser(userId: number) {
  const tasks = await repository.findBy({ userId, endTime: Not(IsNull()) });
  return tasks;
}

export async function getAllDoneFromOneTask(category: string, name: string) {
  const tasks = await repository.find({ 
    where: { category , name, endTime: Not(IsNull()) },
    relations: [ 'user' ]
  });
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
