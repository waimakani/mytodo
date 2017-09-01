
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
  showSubTaskDiv: Map<number, boolean> = new Map();

  constructor(
     private todoService: TodoService
  ) { }

  addToDo(name: string): void {
    this.add(name, null);
  }

  addSubTask(name: string, parentToDoNo: number): void {
    this.add(name, parentToDoNo);
  }

  private add(name: string, parentToDoNo: number): void {
    console.info('create new ' + name);
    name = name.trim();
    if (!name) {return; }
    this.todoService.create(name, parentToDoNo)
      .then(todo => {
        this.todos.push(todo);
      });
  }

  ngOnInit(): void {
    this.todoService.getTodos()
      .then(promisedTodos => this.todos = promisedTodos);
  }


  isAddSubTaskVisible(toDoNo: number): boolean {
    return this.showSubTaskDiv.has(toDoNo) && this.showSubTaskDiv.get(toDoNo);
  }

  toggleAddSubTaskVisibility(toDoNo: number) {
    let oldValue = this.isAddSubTaskVisible(toDoNo);
    this.showSubTaskDiv.set(toDoNo, !oldValue);
  }
}
