import { Component, OnInit } from '@angular/core';
import { AirportService } from './../../service/airport.service';

import { Country } from './../../model/country';
import { Airport } from './../../model/airport';
import { AirportDb } from './../../model/airportdb';

@Component({
  moduleId: module.id,
  selector: 'app-airport',
  templateUrl: './airport.component.html',
  styleUrls: ['./airport.component.css'],
  providers: [ AirportService ]
})
export class AirportComponent implements OnInit {

  countries: Country[];
  airports: Airport[];
  airportsBd: AirportDb[];
  selectedCountry: string;
  selectedIsoCountry: string;
  selectedAirportCode: string;
  selectedAirportName: string;
  showAirports = false;


  constructor(private airportService: AirportService) { }

  ngOnInit() : void {
    this.getAllCountries();
    this.getAirportDb();
  }

  getAllCountries():void {
    this.airportService.getCountries()
                        .subscribe(
                          countries => this.countries = countries,
                          err => {
                              console.log(err);
                          });
  }

  getAirportDb(): void {
    this.airportService.getAirportsDb()
                          .subscribe(
                          airportsBd => this.airportsBd = airportsBd,
                          err => {
                              console.log(err);
                          });
  }

  viewAirportsByCountry(country: String){
    this.airportService.getAirportsByCountry(country)
                        .subscribe(
                        airports => {
                          this.showAirports = true;
                          this.airports = airports;
                        }, 
                        err => {
                          console.log(err);
                        });
  }

  viewAirportsByIsoCountry(country: String){
    this.airportService.getAirportsByIsoCountry(country)
                        .subscribe(
                        airports => {
                          this.showAirports = true;
                          this.airports = airports;
                        }, 
                        err => {
                          console.log(err);
                        });
  }

  viewAirportsByAirportCode(airport: String){
    this.airportService.getAirportsByAirportCode(airport)
                        .subscribe(
                        airports => {
                          this.showAirports = true;
                          this.airports = airports;
                        }, 
                        err => {
                          console.log(err);
                        });
  }

  viewAirportsByAirportName(airport: String){
    this.airportService.getAirportsByAirportName(airport)
                        .subscribe(
                        airports => {
                          this.showAirports = true;
                          this.airports = airports;
                        }, 
                        err => {
                          console.log(err);
                        });
  }


}
