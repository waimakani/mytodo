
import {Component, OnInit} from "@angular/core";
import {Todo} from "./todo";
import {TodoService} from "./todo.service";


@Component({
    selector: 'todo-list',
    templateUrl: './todo-list.component.html',
    styleUrls: [ './todo-list.component.css'],
  })
export class TodoListComponent implements OnInit {
  todos: Todo[];

  constructor(
     private todoService: TodoService
  ) {}

  ngOnInit(): void {
    this.todoService.getTodos()
      .then(promisedTodos => this.todos = promisedTodos);
  }
}
