import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpModule } from '@angular/http';
import { AngularFontAwesomeModule } from 'angular-font-awesome/angular-font-awesome';

import { AppComponent } from './app.component';
import {TodoListComponent} from './todo-list.component';
import {TodoService} from './todo.service';
import {TodoListsComponent} from "./todo-lists.component";
import {FormsModule} from "@angular/forms";

@NgModule({
  declarations: [
    AppComponent,
    TodoListComponent,
    TodoListsComponent
  ],
  imports: [
    BrowserModule,
    HttpModule,
    AngularFontAwesomeModule,
    FormsModule
  ],
  providers: [ TodoService],
  bootstrap: [AppComponent]
})
export class AppModule { }
