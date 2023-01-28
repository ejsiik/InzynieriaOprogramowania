import { Column, CreateDateColumn, Entity, Index, ManyToOne, PrimaryGeneratedColumn } from "typeorm";
import User from "./user.entity.js";

@Entity()
@Index(["name", "category"], { unique: true })
export default class TaskToAdd {
  @PrimaryGeneratedColumn("increment")
  id: number;

  @Column()
  name: string;

  @Column()
  category: string;
}
