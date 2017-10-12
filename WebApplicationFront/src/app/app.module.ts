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


@NgModule({
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    AppRoutesModule,
    AgmCoreModule.forRoot({
      apiKey: 'KEY_MAP'
    })
  ],
  declarations: [
    AppComponent,
    AirportComponent,
    MapsComponent
  ],  
  providers: [
    AirportService,
    MapsService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
