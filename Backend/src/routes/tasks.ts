import { Request, Response } from "express";
import { listRunningTasksForUser, getDoneTasksFromAllUsers,
   getDoneTasksFromCurrentUser, getAllDoneFromOneTask, getMeanFromTask, getBestTimeEnded, getDoneTasksFromCurrentUserHierachy, getDoneTasksFromAllUserHierarchy  } from "../functions/task.js";
import { instanceToPlain } from 'class-transformer';

export async function listRunningTasksForUserRoute(req: Request, res: Response){
  const tasks = await listRunningTasksForUser(res.locals.userId);
  res.json({ tasks: instanceToPlain(tasks) });
}

export async function getDoneTasksFromCurrentUserRoute(req: Request, res: Response) {
  const tasks = await getDoneTasksFromCurrentUser(res.locals.userId);
  res.json({ tasks: instanceToPlain(tasks) });
}

export async function getDoneTasksFromAllUsersRoute(req: Request, res: Response) {
  const tasks = await getDoneTasksFromAllUsers();
  res.json({ tasks: instanceToPlain(tasks) });
}

export async function getDoneTasksFromCurrentUserHierachyRoute(req: Request, res: Response) {
  const tasks = await getDoneTasksFromCurrentUserHierachy(res.locals.userId);
  res.json({ tasks: instanceToPlain(tasks) });
}

export async function getDoneTasksFromAllUserHierarchyRoute(req: Request, res: Response) {
  const tasks = await getDoneTasksFromAllUserHierarchy();
  res.json({ tasks: instanceToPlain(tasks) });
}

export async function getAllDoneFromOneTaskRoute(req: Request, res: Response) {
  const { category, name } = req.params;
  const tasks = await getAllDoneFromOneTask(category, name);
  res.json({ tasks: instanceToPlain(tasks) })
}

export async function getMeanFromTaskRoute(req: Request, res: Response) {
  const { category, name } = req.params;
  const meanTime = await getMeanFromTask(category, name);
  res.json({ meanTime });
}

export async function getBestTimeEndedRoute(req: Request, res: Response) {
  const { category, name } = req.params;
  const bestTime = await getBestTimeEnded(category, name);
  res.json({ bestTime });
}

