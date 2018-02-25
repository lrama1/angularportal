import { Component, OnInit, Input } from '@angular/core';
import {PersonService} from '../person.service';
import {Person} from '../person.model';
import {LazyLoadEvent} from 'primeng/primeng';
import { Router} from '@angular/router';

@Component({
  selector: 'app-person-list',
  templateUrl: './person-list.component.html',
  styleUrls: ['./person-list.component.css']
})
export class PersonListComponent implements OnInit {

  listOfPersons: Person[];
  currentPage = 1;
  pageSize = 5;
  lastPage = 1;
  totalRecords = 0;
  // attribute that can be set by the parent component
  @Input() titleToDisplay: string;
  
  constructor(private personService: PersonService, private router: Router) { }

  ngOnInit() {
    console.log('invoking Person Service');
    //this.listOfPersons = this.personService.getAllPersons();
    this.personService.getPersons(this.currentPage, this.pageSize).subscribe(
      (response) => {
        this.listOfPersons = response.rows;
        this.lastPage = response.lastPage;
        this.totalRecords = response.totalRecords;
        },
      (error) => { console.log(error); }
    );
  }
  
  editRow(person: Person){
    this.router.navigateByUrl('/person/' + person.personId);
  }

  loadPage(event: LazyLoadEvent) {
    const page = (event.first / event.rows) + 1;
    console.log('invoking Person Service');
    //this.listOfPersons = this.personService.getAllPersons();
    this.personService.getPersons(page, event.rows).subscribe(
      (response) => {
        this.listOfPersons = response.rows;
        this.lastPage = response.lastPage;
        this.totalRecords = response.totalRecords;
        },
      (error) => { console.log(error); }
    );
  }

  nextPage() {
    this.currentPage++;
    console.log('invoking Person Service');
    //this.listOfPersons = this.personService.getAllPersons();
    this.personService.getPersons(this.currentPage, this.pageSize).subscribe(
      (response) => {
        this.listOfPersons = response.rows;
        this.lastPage = response.lastPage;
        },
      (error) => { console.log(error); }
    );
  }
  
  previousPage() {
    this.currentPage--;
    console.log('invoking Person Service');
    //this.listOfPersons = this.personService.getAllPersons();
    this.personService.getPersons(this.currentPage, this.pageSize).subscribe(
      (response) => {
        this.listOfPersons = response.rows;
        this.lastPage = response.lastPage;
        },
      (error) => { console.log(error); }
    );
  }
  
}
