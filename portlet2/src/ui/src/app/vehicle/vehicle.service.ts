import {Injectable, OnInit} from '@angular/core';
import { Vehicle } from './vehicle.model';
import {HttpClient} from '@angular/common/http';
import {ListWrapper} from '../app.listwrapper';


@Injectable()
export class VehicleService implements OnInit{
  vehicles: Vehicle[] = [];

  constructor(private http: HttpClient) {}

  ngOnInit() {
  }

  saveVehicle(vehicleToSave: Vehicle) {
    this.vehicles.push(vehicleToSave);
    return this.http.put<Vehicle>('/portlet2/vehicle/' + vehicleToSave.vin, vehicleToSave);
  }

  getVehicle (id: string) {
    return this.http.get<Vehicle>('/portlet2/vehicle/' + id);
  }

  getVehicles (page: number, pageSize: number) {
    //return this.vehicles;
    return this.http.get<ListWrapper<Vehicle>>('/portlet2/vehicles?page=' + page + '&per_page=' + pageSize);
  }
}
