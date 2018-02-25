import {Injectable, OnInit} from '@angular/core';
import { Person } from './person.model';
import {HttpClient} from '@angular/common/http';
import {ListWrapper} from '../app.listwrapper';


@Injectable()
export class PersonService implements OnInit{
  persons: Person[] = [];

  constructor(private http: HttpClient) {}

  ngOnInit() {
  }

  savePerson(personToSave: Person) {
    this.persons.push(personToSave);
    return this.http.put<Person>('/portlet1/person/' + personToSave.personId, personToSave);
  }

  getPerson (id: string) {
    return this.http.get<Person>('/portlet1/person/' + id);
  }

  getPersons (page: number, pageSize: number) {
    //return this.persons;
    return this.http.get<ListWrapper<Person>>('/portlet1/persons?page=' + page + '&per_page=' + pageSize);
  }
}
