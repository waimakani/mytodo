
import {Component, OnInit} from "@angular/core";
import {TodoService} from "./todo.service";
import {TodoList} from "./todoList";


@Component({
    selector: 'todo-lists',
    templateUrl: './todo-lists.component.html',
    styleUrls: [ './todo-lists.component.css'],
  })
export class TodoListsComponent implements OnInit {
  todoLists: TodoList[];

  constructor(
     private todoService: TodoService
  ) {}

  ngOnInit(): void {
    this.todoService.getTodoLists()
      .then(promisedTodoLists => this.todoLists = promisedTodoLists);
  }
}
