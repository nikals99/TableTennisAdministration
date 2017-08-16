import {Injectable} from "@angular/core";
import {Match} from '../model/match';
import {Headers,Http} from "@angular/http";

import 'rxjs/add/operator/toPromise';



@Injectable()
export class MatchService{

  constructor(private http: Http){}


  getMatches(): Promise<Match[]> {
    return this.http.get("/game")
      .toPromise()
      .then(response => response.json()._embedded.matches as Match[])
      .then(match => match.reverse())
      .catch(this.handleError);
  }

  getMatchById(id): Promise<Match[]> {
    return this.http.get("/game/"+id)
      .toPromise()
      .then(response => response.json() as Match)
      .catch(this.handleError);
  }

  private handleError(error: any): Promise<any> {
    console.error('An error occurred', error); // for demo purposes only
    return Promise.reject(error.message || error);
  }


  addMatch(match): Promise<Match>{
    return this.http
      .post("/game",match)
      .toPromise()
      .then(res => res.json().data as Match);
  }

  deleteMatch(id: number): Promise<void> {
    return this.http.delete('game/'+id)
      .toPromise()
      .then(()=>null)
      .catch(this.handleError);
  }
}

