
import {Todo} from './todo';
import {Http, Headers} from '@angular/http';
import {Injectable} from '@angular/core';
import 'rxjs/add/operator/toPromise';
import {TodoList} from "./todoList";

@Injectable()
export class TodoService {

  constructor (
    private http: Http
  ) {}

  private baseUrl = 'http://localhost:4200/api';
  private headers= new Headers({'Content-Type': 'application/json'});

  getTodos(): Promise<Todo[]> {
    return this.http.get(this.baseUrl + '/todos')
      .toPromise()
      .then(response => response.json() as Todo[])
      .catch(this.handleError);
  }

  getTodoLists(): Promise<TodoList[]> {
    return this.http.get(this.baseUrl + '/todolists')
      .toPromise()
      .then(response => response.json() as TodoList[])
      .catch(this.handleError);
  }

  getTodoListById(id : number): Promise<TodoList> {
    return this.http.get(this.baseUrl + '/todoList/' + id)
      .toPromise()
      .then(response => response.json() as TodoList)
      .catch(this.handleError);
  }

  createToDoList(name: string): Promise<TodoList[]>{
    return this.http
      .post(this.baseUrl + '/todolist',
        JSON.stringify({name: name}))
      .toPromise()
      .then(response => response.json() as TodoList[])
      .catch(this.handleError);
  }

  private handleError(error: any): Promise<any> {
    console.error('An error occurred', error);
    return Promise.reject(error.message || error);
  }

  create(name: string, parentToDoNo: number): Promise<Todo> {
    return this.http
      .post(this.baseUrl + '/todo',
        JSON.stringify({name: name, parentToDoNo: parentToDoNo}))
      .toPromise()
      .then(res => res.json() as Todo)
      .catch(this.handleError);
  }

  update(todo: Todo) {
    this.http
    .put(this.baseUrl + '/todos/' + todo.toDoNo, JSON.stringify(todo))
    .toPromise()
    .then(res => res.json() as Todo)
    .catch(this.handleError);
  }
}
