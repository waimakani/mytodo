import { Component } from '@angular/core';
import {TodoList} from "./todoList";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'ToDo-List-NG';
  selectedList: TodoList;

  onNotify(todoList: TodoList): void {
    this.selectedList= todoList;
  }
}
