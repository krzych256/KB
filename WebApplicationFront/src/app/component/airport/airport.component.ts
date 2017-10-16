import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { AirportService } from './../../service/airport.service';
import { MapsService } from './../../service/maps.service';

import { Country } from './../../model/country';
import { Airport } from './../../model/airport';
import { AirportDb } from './../../model/airportdb';
import { GeographicCoordination } from './../../model/geographicCoordination';

import {ButtonModule} from 'primeng/primeng';

@Component({
  moduleId: module.id,
  selector: 'app-airport',
  templateUrl: './airport.component.html',
  styleUrls: ['./airport.component.css']
})
export class AirportComponent implements OnInit {

  countries: Country[];  
  airports: Airport[];
  airportsBd: AirportDb[];  
  selectedCountry: string;
  selectedIsoCountry: string;
  selectedAirportCode: string;
  selectedAirportName: string;
  showAirports: boolean = false;
  showMaps: boolean = false;
  warning: boolean;
  message: string;
  actualLocalization: GeographicCoordination = {};
  airportLocalization: GeographicCoordination = {};    
  distanceBeetwenAB: number; 

  constructor(private airportService: AirportService, private mapsService: MapsService) { }

  ngOnInit() : void {
    this.getAllCountries();
    this.getAirportDb();
    this.getCurrentLocalizaction();
  }

  getAllCountries():void {
    this.airportService.getCountries()
                        .subscribe(
                          countries => this.countries = countries,
                          err => console.log(err));
  }

  getAirportDb(): void {
    this.airportService.getAirportsDb()
                          .subscribe(
                          airportsBd => this.airportsBd = airportsBd,
                          err => console.log(err));
  }

  viewAirportsByCountry(country: String){
    this.airportService.getAirportsByCountry(country)
                        .subscribe(
                        airports => {
                          this.showAirports = true;
                          this.airports = airports;                          
                        }, 
                        err => console.log(err));
  }

  viewAirportsByIsoCountry(country: String){
    this.airportService.getAirportsByIsoCountry(country)
                        .subscribe(
                        airports => {
                          this.showAirports = true;
                          this.airports = airports;
                        }, 
                        err => console.log(err));
  }

  viewAirportsByAirportCode(airport: String){
    this.airportService.getAirportsByAirportCode(airport)
                        .subscribe(
                        airports => {
                          this.showAirports = true;
                          this.airports = airports;
                        }, 
                        err => console.log(err));
  }

  viewAirportsByAirportName(airport: String){
    this.airportService.getAirportsByAirportName(airport)
                        .subscribe(
                        airports => {
                          this.showAirports = true;
                          this.airports = airports;
                        }, 
                        err => console.log(err));
  }

  getCurrentLocalizaction(): void {
    this.warning = false;
    this.message = "";

    if (navigator.geolocation) {
      this.mapsService.getCurrentPosition().subscribe(
                (position: Position) => {
                  this.actualLocalization.latitude = position.coords.latitude;
                  this.actualLocalization.longitude = position.coords.longitude;                  
                },
                (error: PositionError) => {
                    if (error.code > 0) {
                        switch (error.code) {
                            case error.PERMISSION_DENIED:
                                this.message = 'permission denied';
                                break;
                            case error.POSITION_UNAVAILABLE:
                                this.message = 'position unavailable';
                                break;
                            case error.TIMEOUT:
                                this.message = 'position timeout';
                                break;
                        }
                        this.warning = true;
                    }
                },
                () => console.log('Geolocation service: completed.'));

        } else {
            this.message = "browser doesn't support geolocation";
            this.warning = true;
        }
  }

  showDistanceOnMap(airport: Airport): void {    
    this.airportLocalization.latitude = this.mapsService.convertDMSToDecimal(airport.latitudeNpeerS, Number(airport.latitudeDegree), Number(airport.latitudeMinute), Number(airport.latitudeSecond));
    this.airportLocalization.longitude = this.mapsService.convertDMSToDecimal(airport.longitudeEperW, Number(airport.longitudeDegree), Number(airport.longitudeMinute), Number(airport.longitudeSeconds));
    this.distanceBeetwenAB = this.mapsService.distanceInKmBetweenEarthCoordinates(this.actualLocalization.latitude, this.actualLocalization.longitude, this.airportLocalization.latitude, this.airportLocalization.longitude);
    this.showMaps = true;
    window.scrollTo(0, 0);
  }

}