import {Component, OnInit} from '@angular/core';
import {Match} from "../model/match";
import {Router} from "@angular/router"
import {MatchService} from "../services/match.service";
import {FormsModule} from "@angular/forms";


@Component({
  selector: 'app-add-match',
  templateUrl: './add-match.component.html',
  styleUrls: ['./add-match.component.css'],
  providers: [MatchService]
})
export class AddMatchComponent implements OnInit {
  match: Match;

  constructor(private matchService: MatchService, private router: Router,private formsModule:FormsModule) {
  }


  onSubmit(form: any): void {
    this.matchService.addMatch(form).then((): void => {
      this.router.navigateByUrl("/")
    });

  }

  ngOnInit() {
  }

}
