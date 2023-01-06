import { Request, Response } from "express";
import { listRunningTasksForUser } from "../functions/task.js";
import { instanceToPlain } from 'class-transformer';

export async function listRunningTasksForUserRoute(req: Request, res: Response){
  const tasks = await listRunningTasksForUser(res.locals.userId);
  res.json({ tasks: instanceToPlain(tasks) });
}


