import { IsNull } from "typeorm";
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
