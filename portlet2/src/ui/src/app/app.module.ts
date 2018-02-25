import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule} from '@angular/forms';
import { RouterModule, Routes } from '@angular/router';
import { APP_BASE_HREF} from '@angular/common';

import { ButtonModule, DataTableModule, PanelModule, SharedModule } from 'primeng/primeng';
import { BrowserAnimationsModule} from '@angular/platform-browser/animations';
import { AppComponent } from './app.component';
import { VehicleListComponent } from './vehicle/vehicle-list/vehicle-list.component';
import { VehicleEditComponent } from './vehicle/vehicle-edit/vehicle-edit.component';
import {VehicleService} from './vehicle/vehicle.service';
import { HomeComponent } from './home/home.component';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {MyLoginInterceptor} from './app.login.interceptor';

const appRoutes: Routes = [  
  { path: 'vehicles', component: VehicleListComponent},
  { path: 'vehicle/:id', component: VehicleEditComponent},
  { path: '', component: HomeComponent}
];

@NgModule({
  declarations: [
    AppComponent,
    VehicleListComponent,
    VehicleEditComponent,
    HomeComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    RouterModule.forRoot(appRoutes,  { useHash: true }),    
    DataTableModule,
    SharedModule,
    BrowserAnimationsModule,
    PanelModule,
    ButtonModule
  ],
  providers: [/*{provide: APP_BASE_HREF, useValue: '/portlet2'},*/{ provide: HTTP_INTERCEPTORS, useClass: MyLoginInterceptor, multi: true }, VehicleService],
  bootstrap: [AppComponent]
})
export class AppModule { }
