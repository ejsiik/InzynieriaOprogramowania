import express from 'express';
import { verifyJWT } from '../functions/jwt.js';
import HttpException from '../http-exception.js';
import { addTask } from './add-task.js';
import { changeTaskStatus } from './change-task-status.js';
import { getCurrentUser } from './get-current-user.js';
import { listRunningTasksForUserRoute, getDoneTasksFromCurrentUserRoute, getDoneTasksFromAllUsersRoute, getAllDoneFromOneTaskRoute, getMeanFromTaskRoute, getBestTimeEndedRoute, getDoneTasksFromCurrentUserHierachyRoute, getDoneTasksFromAllUserHierarchyRoute } from './tasks.js';
import { loginRoute } from './login.js';
import { findUser } from '../functions/user.js';
import { getDoneTasksFromAllUserHierarchy } from '../functions/task.js';

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

// // return all tasks from one user with times
router.get("/tasks/me/done", getDoneTasksFromCurrentUserRoute);

router.get("/tasks/me/done/hierarchy", getDoneTasksFromCurrentUserHierachyRoute);

router.use(async (req, res, next) => {
  const user = await findUser(res.locals.userId);
  if (!user.isAdmin) {
    throw new HttpException(403, "Operacja wymaga uprawnień.");
  }

  next();
});
router.get("/tasks/done/hierarchy", getDoneTasksFromAllUserHierarchyRoute);

// return all done tasks for all users with times
router.get("/tasks/done", getDoneTasksFromAllUsersRoute);

// return all done from one task
router.get("/tasks/done/:category/:name", getAllDoneFromOneTaskRoute);

// return mean from one task
router.get("/tasks/done/:category/:name/mean", getMeanFromTaskRoute);

// retrun best time ended task
router.get("/tasks/done/:category/:name/best", getBestTimeEndedRoute);


export default router;
