import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {HttpModule} from "@angular/http";
import {RouterModule} from "@angular/router";
import {FormsModule} from "@angular/forms";

import {MatchesComponent} from './matchesComponent/matches.component';
import {AddMatchComponent} from './add-matchComponent/add-match.component';
import {app} from "./app.component";
import { TableComponent } from './table/table.component';


@NgModule({
  declarations: [
    MatchesComponent,
    AddMatchComponent,
    app,
    TableComponent,
  ],
  imports: [
    BrowserModule,
    HttpModule,
    FormsModule,
    RouterModule.forRoot([
      {
        path: '',
        component: MatchesComponent
      },
      {
        path: 'addGame',
        component: AddMatchComponent
      },
      {
        path: 'gettable',
        component: TableComponent
      }
    ])
  ],
  providers: [],
  bootstrap: [app]
})
export class AppModule {
}
