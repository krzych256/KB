import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { AppRoutesModule } from './app-routes';
import { AgmCoreModule } from '@agm/core';

import { AppComponent } from './app.component';
import { AirportComponent } from './component/airport/airport.component';
import { MapsComponent } from './component/maps/maps.component';

import { AirportService } from './service/airport.service';
import { MapsService } from './service/maps.service';
import { CounterComponent } from './component/test/counter.component';
import { ViewComponent } from './component/test/view.component';

import { AccordionModule } from 'primeng/primeng'; 
import { ButtonModule } from 'primeng/primeng';

import { DataTableModule, SharedModule } from 'primeng/primeng';
 

@NgModule({
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    AppRoutesModule,
    ButtonModule,
    AccordionModule,
    DataTableModule,
    SharedModule,
    AgmCoreModule.forRoot({
      apiKey: 'API-KEY'
    })
  ],
  declarations: [
    AppComponent,
    AirportComponent,
    MapsComponent,
    CounterComponent,
    ViewComponent    
  ],  
  providers: [
    AirportService,
    MapsService    
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
