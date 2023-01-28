import { AppDataSource } from "./data-source.js";
import { createUser } from "./functions/user.js";
import TaskToAdd from "./models/task-to-add.js";
import Task from "./models/task.entity.js";

await AppDataSource.initialize();
const manager = AppDataSource.manager;

try {
  const users = await Promise.all([
    createUser("jaidananaia", "12345678", true),
    createUser("kleoncarles", "zaq1@WSX", false),
    createUser("adamjanika", "dezadek", false)
  ]);

  console.log(`Utworzono ${users.length} użytkowików:`);
  console.log(users.map(u => u.login).join("\n"));
}
catch {}

function createTaskToAdd(category: string, name: string) {
  const task = new TaskToAdd();
  manager.merge(TaskToAdd, task, { category, name });

  return task;
}
try {
  const tasks = [
    createTaskToAdd("Phone", "LCD"),
    createTaskToAdd("Phone", "Battery"),
    createTaskToAdd("Phone", "USB port"),
    createTaskToAdd("Phone", "Camera"),
    createTaskToAdd("Phone", "Speaker"),
    createTaskToAdd("Phone", "Software"),
    createTaskToAdd("Phone", "Clean up"),
    createTaskToAdd("Computer", "CPU"),
    createTaskToAdd("Computer", "GPU"),
    createTaskToAdd("Computer", "RAM"),
    createTaskToAdd("Computer", "PSU"),
    createTaskToAdd("Computer", "Motherboard"),
    createTaskToAdd("Computer", "Software"),
    createTaskToAdd("Computer", "Clean up"),
    createTaskToAdd("Console", "Controller"),
    createTaskToAdd("Console", "Disc"),
    createTaskToAdd("Console", "HDMI controller"),
    createTaskToAdd("Console", "PSU"),
    createTaskToAdd("Console", "Software"),
    createTaskToAdd("Console", "Clean up"),
  ];


  await manager.save(tasks);

  console.log(`Utworzono ${tasks.length} zadań:`);
  console.log(tasks.map(t => `${t.category}:${t.name}`).join("\n"));
}
catch (err) {}



function createTask(category: string, name: string, userId: number, createdAt?: Date, endTime?: Date) {
  const task = new Task();
  manager.merge(Task, task, { category, name, userId, createdAt, endTime });

  return task;
}

try {
  const tasks = [
    createTask("Phone", "Camera", 1, new Date("2022-07-21T12:32:43Z"), new Date("2022-07-24T08:03:05Z")),
    createTask("Phone", "Speaker", 1, new Date("2022-07-21T12:32:43Z"), new Date("2022-07-23T15:03:01Z")),
    createTask("Phone", "LCD", 1, new Date("2022-07-21T12:32:43Z"), new Date("2022-07-24T15:43:21Z")),
    createTask("Phone", "USB port", 1, new Date("2022-12-31T10:42:22Z")),
    createTask("Computer", "CPU", 2, new Date("2022-09-10T09:12:40Z"), new Date("2022-09-23T12:11:23Z")),
    createTask("Computer", "Motherboard", 2, new Date("2022-09-21T12:32:43Z"), new Date("2022-09-24T11:43:17Z")),
    createTask("Computer", "RAM", 2, new Date("2022-10-12T10:00:00Z"), new Date("2022-10-13T12:12:17Z")),
    createTask("Computer", "RAM", 2, new Date("2022-11-29T12:55:28Z"), new Date("2022-12-01T13:27:18Z")),
    createTask("Computer", "Clean up", 2, new Date("2022-12-30T11:11:11Z")),
    createTask("Console", "Controller", 3, new Date("2023-01-03T10:15:00Z"), new Date("2023-01-04T08:00:00Z")),
    createTask("Console", "HDMI controller", 3, new Date("2023-01-06T14:05:00"), new Date("2023-01-07T12:15:00")),
    createTask("Console", "Software", 3, new Date("2023-01-10T08:00:00Z"), new Date("2023-01-10T08:30:00Z")),
    createTask("Phone", "Software", 3, new Date("2023-01-11T10:12:13Z")),
    createTask("Computer", "Software", 3, new Date("2023-01-11T13:15:00Z"))
  ];

  await manager.save(tasks);

  console.log(`Utworzono ${tasks.length} zadań:`);
  console.log(tasks.map(t => `${t.category}:${t.name} dla użytkownika ${t.userId}`).join("\n"));
}
catch {}
