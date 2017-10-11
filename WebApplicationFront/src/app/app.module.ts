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


@NgModule({
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    AppRoutesModule,
    AgmCoreModule.forRoot({
      apiKey: 'AIzaSyBu-916DdpKAjTmJNIgngS6HL_kDIKU0aU'
    })
  ],
  declarations: [
    AppComponent,
    AirportComponent,
    MapsComponent
  ],  
  providers: [AirportService],
  bootstrap: [AppComponent]
})
export class AppModule { }
