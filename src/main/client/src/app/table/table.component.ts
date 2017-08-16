import { Component, OnInit } from '@angular/core';
import {TableService} from "../services/table.service";
import {Table} from "../model/table";

@Component({
  selector: 'app-table',
  templateUrl: './table.component.html',
  styleUrls: ['./table.component.css'],
  providers: [TableService]
})

export class TableComponent implements OnInit {
  table: Table[];
  constructor(private tableService: TableService) { }

  getTable(): void{
    this.tableService.getTable().then(table => this.table = table)
  }

  ngOnInit() {
    this.getTable();
  }

}
