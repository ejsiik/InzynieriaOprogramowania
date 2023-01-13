import { instanceToPlain } from "class-transformer";
import { Request, Response } from "express";
import { changeToDone } from "../functions/task.js";


export async function changeTaskStatus(req: Request, res: Response) {
  const { taskId } = req.params;
  const task = await changeToDone(parseInt(taskId));
  return res.status(200).json({ task: instanceToPlain(task)});
}
