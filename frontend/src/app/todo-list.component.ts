
import {Component, Input, OnInit} from '@angular/core';
import { Todo } from './todo';
import { TodoService } from './todo.service';
import {TodoList} from "./todoList";


@Component({
    selector: 'todo-list',
    templateUrl: './todo-list.component.html',
    styleUrls: [ './todo-list.component.css'],
  })
export class TodoListComponent implements OnInit {
  @Input() todoList: TodoList;
  todos: Todo[];
  subTaskDivShownForToDoNo: number;
  todoName: string;

  constructor(
     private todoService: TodoService
  ) { }

  addToDo(): void {
    this.add(this.todoName, null);
    this.todoName = '';
  }

  addSubTask(name: string, parentToDoNo: number): void {
    this.add(name, parentToDoNo);
    this.toggleAddSubTaskVisibility(parentToDoNo);
  }

  private add(name: string, parentToDoNo: number): void {
    name = name.trim();
    if (!name) {return; }
    this.todoService.create(name, parentToDoNo)
      .then(() => {
        this.setTodos();
      });
  }

  ngOnInit(): void {
    this.setTodos();
  }

  private setTodos() {
    this.todoService.getTodos()
      .then(promisedTodos => {
        this.todos = promisedTodos.sort((t1, t2) => {
          if (t1.completed !== t2.completed) {
            return t1.completed ? 1 : -1;
          }
          return t2.toDoNo - t1.toDoNo;
        });
      });
  }


  isAddSubTaskVisible(toDoNo: number): boolean {
    return this.subTaskDivShownForToDoNo === toDoNo;
  }

  toggleAddSubTaskVisibility(toDoNo: number) {
    if (this.subTaskDivShownForToDoNo === toDoNo) {
      this.subTaskDivShownForToDoNo = null;
    } else {
      this.subTaskDivShownForToDoNo = toDoNo;
    }
  }

  hasSubTasks(parentToDoNo: number): boolean {
    return this.getSubTasks(parentToDoNo).length > 0;
  }

  getSubTasks(parentToDoNo: number): Todo[] {
    return this.todos.filter(todo => todo.parentToDoNo===parentToDoNo);
  }

  getRootToDos(): Todo[] {
    return this.todos.filter(todo => todo.parentToDoNo==null);
  }

  completeTask(todo: Todo) {
    todo.completed = true;
    this.todoService.update(todo)
      .then(() => {
        this.setTodos();
      });
  }

  deleteTask(todo: Todo) {
    this.todoService.delete(todo)
      .then(() => {
        this.setTodos();
      });
  }
}
