import { Component, OnInit } from '@angular/core';
import {PersonListComponent } from "../person/person-list/person-list.component";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  }

}
