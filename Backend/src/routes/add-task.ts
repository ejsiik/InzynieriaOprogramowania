import { instanceToPlain } from "class-transformer";
import { Request, Response } from "express";
import { createTask } from "../functions/task.js";
import HttpException from "../http-exception.js";

interface ITaskData {
  category: string;
  name: string;
};

export async function addTask(req: Request, res: Response) {
  const { category, name } = req.body as ITaskData;

  if(!category || !name) {
    throw new HttpException(400, "Brak danych.");
  }
  const task = await createTask(category, name, res.locals.userId);

  return res.status(201).json({ task: instanceToPlain(task) });
}

