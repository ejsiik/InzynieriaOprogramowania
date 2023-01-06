import { instanceToPlain } from "class-transformer";
import { Request, Response } from "express";
import { findUser } from "../functions/user.js";

export async function getCurrentUser(req: Request, res: Response) {
  const user = await findUser(res.locals.userId);
  res.json({ user: instanceToPlain(user) });
}
