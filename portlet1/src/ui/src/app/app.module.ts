import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule} from '@angular/forms';
import { RouterModule, Routes } from '@angular/router';
import { APP_BASE_HREF} from '@angular/common';

import { ButtonModule, DataTableModule, PanelModule, SharedModule } from 'primeng/primeng';
import { BrowserAnimationsModule} from '@angular/platform-browser/animations';
import { AppComponent } from './app.component';
import { PersonListComponent } from './person/person-list/person-list.component';
import { PersonEditComponent } from './person/person-edit/person-edit.component';
import {PersonService} from './person/person.service';
import { HomeComponent } from './home/home.component';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {MyLoginInterceptor} from './app.login.interceptor';

const appRoutes: Routes = [
  { path: 'persons', component: PersonListComponent},
  { path: 'person/:id', component: PersonEditComponent},
  { path: '', component: HomeComponent}
];

@NgModule({
  declarations: [
    AppComponent,
    PersonListComponent,
    PersonEditComponent,
    HomeComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    RouterModule.forRoot(appRoutes, { useHash: true }),
    DataTableModule,
    SharedModule,
    BrowserAnimationsModule,
    PanelModule,
    ButtonModule
  ],
  providers: [/*{provide: APP_BASE_HREF, useValue: '/portlet1'},*/{ provide: HTTP_INTERCEPTORS, useClass: MyLoginInterceptor, multi: true }, PersonService],
  bootstrap: [AppComponent]
})
export class AppModule { }
