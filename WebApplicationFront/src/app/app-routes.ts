import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';

import { AirportComponent } from './component/airport/airport.component';
import { MapsComponent } from './component/maps/maps.component';

import { ViewComponent } from './component/test/view.component';

const routes: Routes = [ 
  { path: 'airports',  component: AirportComponent },
  { path: 'test',  component: ViewComponent },
  
];
@NgModule({
  imports: [ RouterModule.forRoot(routes) ],
  exports: [ RouterModule ]
})
export class AppRoutesModule { }
