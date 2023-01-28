import "reflect-metadata";
import express from 'express';
// Libka, która sprawia, że asynchroniczne błędy działają z middleware do obsługi błędów w expressie.
import 'express-async-errors';
import morgan from 'morgan';
import { AppDataSource } from "./data-source.js";
import HttpException from "./http-exception.js";
import mainRouter from "./routes/index.js";
import cors from "cors";

export default async function main() {
  await AppDataSource.initialize();

  const app = express();

  app.use(cors({
    origin: "*"
  }));

  app.use(express.json());

  app.use(morgan("tiny"));

  app.use("/", mainRouter);

  app.use((req, res) => {
    res.status(404).json({ message: "Not found you fuckin' idiot." });
  });

  app.use((err, req, res, next) => {
    console.error(err);

    if (err instanceof HttpException) {
      return res.status(err.statusCode).json({ error: err.message });
    }

    if (err.status) {
      switch(err.status) {
        case 400:
          res.status(400).json({ error: "Błędne dane." });
      }
    }

    return res.status(500).json({ error: "Internal Server Error" });
  });

  app.listen(1338, "0.0.0.0", () => {
    console.log("App is listening on 0.0.0.0:1337");
  });
}
