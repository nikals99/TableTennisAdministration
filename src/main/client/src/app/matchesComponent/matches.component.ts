import {Component, OnInit} from '@angular/core';
import {Match} from "../model/match";
import {MatchService} from "../services/match.service";
import {Router} from "@angular/router";


@Component({
  selector: 'app-matches',
  templateUrl: './matches.component.html',
  styleUrls: ['./matches.component.css'],
  providers: [MatchService]
})

export class MatchesComponent implements OnInit {
  selectedMatch: Match;
  matches: Match[];

  constructor(private matchService: MatchService, private router: Router) {
  }

  onSelect(match: Match): void {
    this.selectedMatch = match;

  }

  getMatches(): void {
    this.matchService.getMatches().then(matches => this.matches = matches);
  }

  deleteMatch(match): void {
    this.matchService.deleteMatch(match.ident).then(() => {
      this.matches.splice(this.matches.indexOf(match), 1);
      if(this.selectedMatch === match){
        this.selectedMatch = null;
      }
    });
  }

  ngOnInit(): void {
    this.getMatches();
  }
}
