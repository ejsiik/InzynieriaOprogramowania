import express from 'express';
import { verifyJWT } from '../functions/jwt.js';
import { listRunningTasksForUser } from '../functions/task.js';
import HttpException from '../http-exception.js';
import { addTask } from './add-task.js';
import { changeTaskStatus } from './change-task-status.js';
import { getCurrentUser } from './get-current-user.js';
import { listRunningTasksForUserRoute } from './list-tasks.js';
import { loginRoute } from './login.js';

const router = express.Router();

router.post("/login", loginRoute);

router.use(async (req, res, next) => {
  const auth = req.header("Authorization");
  if (!auth) {
    throw new HttpException(401, "Operacja wymaga zalogowania.");
  }

  const token = auth.replace("Bearer ", "");
  const data = await verifyJWT(token);
  res.locals.userId = parseInt(data.sub);
  next();
});

router.post("/tasks", addTask);

router.get("/running-tasks", listRunningTasksForUserRoute);

router.get("/me", getCurrentUser);

router.put("/running-tasks/:taskId/change-status", changeTaskStatus);

export default router;
