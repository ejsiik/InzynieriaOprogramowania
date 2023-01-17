import { Column, Entity, OneToMany, PrimaryGeneratedColumn } from "typeorm";
import Task from "./task.entity.js";
import { Exclude } from 'class-transformer';

@Entity()
export default class User {
  @PrimaryGeneratedColumn("increment")
  id: number;

  @Column({ unique: true })
  login: string;

  @Exclude()
  @Column()
  password: string;

  @Column()
  isAdmin: boolean;

  @OneToMany(() => Task, o => o.user)
  tasks: Task[];
}
