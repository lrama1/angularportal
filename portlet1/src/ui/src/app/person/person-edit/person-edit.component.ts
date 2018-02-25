import { Component, OnInit } from '@angular/core';
import { PersonService } from '../person.service';
import { Person } from '../person.model';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-person-edit',
  templateUrl: './person-edit.component.html',
  styleUrls: ['./person-edit.component.css']
})
export class PersonEditComponent implements OnInit {
  person: Person = new Person(
        '' 
        , ''       , ''     );

  constructor(private personService: PersonService, private route: ActivatedRoute) { }

  ngOnInit() {
    // param name should match what you defined in appRoutes
    console.log('loading person:' + this.route.snapshot.params['id']);
    this.personService.getPerson(this.route.snapshot.params['id']).subscribe(
      (response) => { this.person = response; },
      (error) => { console.log(error); }
    );
  }

  savePerson() {
    this.personService.savePerson(this.person).subscribe(
      (response) => { this.person = response; },
      (error) => { console.log(error); }
    );
  }

}
