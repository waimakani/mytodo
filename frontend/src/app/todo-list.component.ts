
import { Component, OnInit } from '@angular/core';
import { Todo } from './todo';
import { TodoService } from './todo.service';


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

  add(name: string): void {
    console.info('create new ' + name);
    name = name.trim();
    if (!name) {return; }
    this.todoService.create(name)
      .then(todo => {
        this.todos.push(todo);
      });
  }

  ngOnInit(): void {
    this.todoService.getTodos()
      .then(promisedTodos => this.todos = promisedTodos);
  }
}
