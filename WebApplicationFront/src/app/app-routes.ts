import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';

import { AirportComponent } from './component/airport/airport.component';
import { MapsComponent } from './component/maps/maps.component';

const routes: Routes = [
  { path: '',  component: AirportComponent },
  { path: 'maps',  component: MapsComponent },
  
];
@NgModule({
  imports: [ RouterModule.forRoot(routes) ],
  exports: [ RouterModule ]
})
export class AppRoutesModule { }
