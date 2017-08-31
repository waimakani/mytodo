
import {Todo} from './todo';
import {Http, Headers} from '@angular/http';
import {Injectable} from '@angular/core';
import 'rxjs/add/operator/toPromise';

@Injectable()
export class TodoService {

  constructor (
    private http: Http
  ) {}

  private baseUrl = 'http://localhost:4567';
  private headers= new Headers({'Content-Type': 'application/json'});

  getTodos(): Promise<Todo[]> {
    return this.http.get(this.baseUrl + '/todos')
      .toPromise()
      .then(response => response.json() as Todo[])
      .catch(this.handleError);
  }

  private handleError(error: any): Promise<any> {
    console.error('An error occurred', error);
    return Promise.reject(error.message || error);
  }

  create(name: string): Promise<Todo> {
    return this.http
      .post(this.baseUrl + '/todo', JSON.stringify({name: name}), {headers: this.headers})
      .toPromise()
      .then(res => res.json() as Todo)
      .catch(this.handleError);
  }
}
