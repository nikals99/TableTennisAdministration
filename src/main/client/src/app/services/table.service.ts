import {Injectable} from "@angular/core";
import {Table} from "../model/table"
import {Headers,Http} from "@angular/http";

import 'rxjs/add/operator/toPromise';



@Injectable()
export class TableService{
  constructor(private http: Http){}

  getTable(): Promise<Table[]>{
    return this.http.get("/table")
      .toPromise()
      .then(response => response.json() as Table[])
      .catch(this.handleError);
  }

  private handleError(error: any): Promise<any> {
    console.error('An error occurred', error); // for demo purposes only
    return Promise.reject(error.message || error);
  }

}

