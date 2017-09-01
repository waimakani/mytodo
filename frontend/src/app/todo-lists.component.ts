
import {Component, EventEmitter, OnInit, Output} from "@angular/core";
import {TodoService} from "./todo.service";
import {TodoList} from "./todoList";


@Component({
    selector: 'todo-lists',
    templateUrl: './todo-lists.component.html',
    styleUrls: [ './todo-lists.component.css'],
  })
export class TodoListsComponent implements OnInit {
  todoLists: TodoList[];
  @Output() notify: EventEmitter<TodoList>= new EventEmitter();

  constructor(
     private todoService: TodoService
  ) {}

  onClick(todoList: TodoList): void {
    this.notify.emit(todoList);
  }

  ngOnInit(): void {
    this.todoService.getTodoLists()
      .then(promisedTodoLists => this.todoLists = promisedTodoLists);
  }

  createToDoList(name: string): void {
      this.todoService.createToDoList(name)
        .then(promisedTodoLists => this.todoLists = promisedTodoLists);
  }

  searchToDoList(id: number): void{
      this.todoService.getTodoListById(id)
        .then(promisedTodoList => this.todoLists = [promisedTodoList]);
  }
}
