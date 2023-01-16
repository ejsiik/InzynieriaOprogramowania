import { compare, hash } from "bcrypt";
import { AppDataSource } from "../data-source.js";
import HttpException from "../http-exception.js";
import User from "../models/user.entity.js";
import { signJWT } from "./jwt.js";

const repository = AppDataSource.getRepository(User);

export async function createUser(login: string, plainPassword: string, isAdmin: boolean) {
  if (await doesUserExists(login)) {
    throw new HttpException(409, "Użytkownik już istnieje.");
  }

  const user = new User();
  const password = await hash(plainPassword, 10);

  repository.merge(user, { login, password, isAdmin });

  await repository.save(user);
  return user;
}

export async function loginUser(login: string, password: string) {
  const user = await repository.findOneBy({ login });

  if (!user || !(await compare(password, user.password))) {
    throw new HttpException(401, "Błędne dane.");
  }
  
  return signJWT({ sub: user.id });
}

export async function findUser(userId: number){
  const user = await repository.findOneBy({ id: userId });
  return user;
}

export async function doesUserExists(login: string) {
  const user = await repository.findOneBy({ login });
  return !!user;
}
