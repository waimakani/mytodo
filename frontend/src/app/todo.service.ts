
import {Todo} from "./todo";
import {Http, Headers} from "@angular/http";
import {Injectable} from "@angular/core";
import "rxjs/add/operator/toPromise";

@Injectable()
export class TodoService {

  constructor (
    private http: Http
  ) {}

  getTodos(): Promise<Todo[]> {
    var example: Todo[] = [{title: "asdf"}];
    console.info(example);
    console.info(JSON.stringify(example));

    return this.http.get("http://localhost:4567/todos")
      .toPromise()
      .then(response => response.json() as Todo[])
      .catch(this.handleError);
  }

  private handleError(error: any): Promise<any> {
    console.error('An error occurred', error);
    return Promise.reject(error.message || error);
  }
}
