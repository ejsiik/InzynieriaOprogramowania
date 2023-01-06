import { Request, Response } from "express";
import { loginUser } from "../functions/user.js";
import HttpException from "../http-exception.js";

interface ILoginData {
  login?: string;
  password?: string;
}

export async function loginRoute(req: Request, res: Response) {
  const { login, password } = req.body as ILoginData;

  if (!login || !password) {
    throw new HttpException(400, "Brak danych.");
  }

  const token = await loginUser(login, password);

  return res.json({ token: token });
}
