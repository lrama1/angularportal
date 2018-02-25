import { Component, OnInit } from '@angular/core';
import { VehicleService } from '../vehicle.service';
import { Vehicle } from '../vehicle.model';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-vehicle-edit',
  templateUrl: './vehicle-edit.component.html',
  styleUrls: ['./vehicle-edit.component.css']
})
export class VehicleEditComponent implements OnInit {
  vehicle: Vehicle = new Vehicle(
        '' 
        , ''       , ''       , ''     );

  constructor(private vehicleService: VehicleService, private route: ActivatedRoute) { }

  ngOnInit() {
    // param name should match what you defined in appRoutes
    console.log('loading vehicle:' + this.route.snapshot.params['id']);
    this.vehicleService.getVehicle(this.route.snapshot.params['id']).subscribe(
      (response) => { this.vehicle = response; },
      (error) => { console.log(error); }
    );
  }

  saveVehicle() {
    this.vehicleService.saveVehicle(this.vehicle).subscribe(
      (response) => { this.vehicle = response; },
      (error) => { console.log(error); }
    );
  }

}
