import { Request, Response } from "express";
import { getTasksToAdd } from "../functions/task.js";

export async function getTasksToAddRoute(req: Request, res: Response) {
  const tasks = await getTasksToAdd();
  return res.json({ tasks: tasks });
}
