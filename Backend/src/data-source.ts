import { DataSource } from "typeorm";
import TaskToAdd from "./models/task-to-add.js";
import Task from "./models/task.entity.js";
import User from "./models/user.entity.js";

export const AppDataSource = new DataSource({
  type: "sqlite",
  database: "./db.sqlite",
  synchronize: true,
  entities: [User, Task, TaskToAdd]
});
