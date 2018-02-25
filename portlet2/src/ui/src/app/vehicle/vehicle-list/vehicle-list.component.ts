import { Component, OnInit, Input } from '@angular/core';
import {VehicleService} from '../vehicle.service';
import {Vehicle} from '../vehicle.model';
import {LazyLoadEvent} from 'primeng/primeng';
import { Router} from '@angular/router';

@Component({
  selector: 'app-vehicle-list',
  templateUrl: './vehicle-list.component.html',
  styleUrls: ['./vehicle-list.component.css']
})
export class VehicleListComponent implements OnInit {

  listOfVehicles: Vehicle[];
  currentPage = 1;
  pageSize = 5;
  lastPage = 1;
  totalRecords = 0;
  // attribute that can be set by the parent component
  @Input() titleToDisplay: string;
  
  constructor(private vehicleService: VehicleService, private router: Router) { }

  ngOnInit() {
    console.log('invoking Vehicle Service');
    //this.listOfVehicles = this.vehicleService.getAllVehicles();
    this.vehicleService.getVehicles(this.currentPage, this.pageSize).subscribe(
      (response) => {
        this.listOfVehicles = response.rows;
        this.lastPage = response.lastPage;
        this.totalRecords = response.totalRecords;
        },
      (error) => { console.log(error); }
    );
  }
  
  editRow(vehicle: Vehicle){
    this.router.navigateByUrl('/vehicle/' + vehicle.vin);
  }

  loadPage(event: LazyLoadEvent) {
    const page = (event.first / event.rows) + 1;
    console.log('invoking Vehicle Service');
    //this.listOfVehicles = this.vehicleService.getAllVehicles();
    this.vehicleService.getVehicles(page, event.rows).subscribe(
      (response) => {
        this.listOfVehicles = response.rows;
        this.lastPage = response.lastPage;
        this.totalRecords = response.totalRecords;
        },
      (error) => { console.log(error); }
    );
  }

  nextPage() {
    this.currentPage++;
    console.log('invoking Vehicle Service');
    //this.listOfVehicles = this.vehicleService.getAllVehicles();
    this.vehicleService.getVehicles(this.currentPage, this.pageSize).subscribe(
      (response) => {
        this.listOfVehicles = response.rows;
        this.lastPage = response.lastPage;
        },
      (error) => { console.log(error); }
    );
  }
  
  previousPage() {
    this.currentPage--;
    console.log('invoking Vehicle Service');
    //this.listOfVehicles = this.vehicleService.getAllVehicles();
    this.vehicleService.getVehicles(this.currentPage, this.pageSize).subscribe(
      (response) => {
        this.listOfVehicles = response.rows;
        this.lastPage = response.lastPage;
        },
      (error) => { console.log(error); }
    );
  }
  
}
