import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions } from "@angular/http";
import { Observer } from 'rxjs/Observer';
import { Observable } from 'rxjs/Observable';

import 'rxjs/add/operator/toPromise';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';

import { Country } from './../model/country';
import { Airport } from './../model/airport';
import { AirportDb } from './../model/airportdb';

@Injectable()
export class AirportService {

  private allCountriesUrl = 'http://localhost:8080/api/allCountries'; 
  private airportsByCountryUrl = 'http://localhost:8080/api/airports/country/';
  private airportsByIsoCountryUrl = 'http://localhost:8080/api/airports/iso/';
  private allAirportsDbUrl = 'http://localhost:8080/api/airports/';
  private airportsByAirportCodeUrl = 'http://localhost:8080/api/airports/code/';
  private airportsByAirportNameUrl = 'http://localhost:8080/api/airports/name/';
  
  constructor(private http: Http) { }

  getCountries(): Observable<Country[]>{
    return this.http.get(this.allCountriesUrl)
                          .map((res:Response) => res.json())                         
                          .catch((error:any) => Observable.throw(error.json().error || 'Server error'));
  }

  getAirportsDb(): Observable<AirportDb[]>{
      return this.http.get(this.allAirportsDbUrl)
                          .map((res:Response) => res.json())                         
                          .catch((error:any) => Observable.throw(error.json().error || 'Server error'));
  }

  getAirportsByCountry(country: String) : Observable<Airport[]> {
    return this.http.get(this.airportsByCountryUrl + country)
                          .map((res:Response) => res.json())                         
                          .catch((error:any) => Observable.throw(error.json().error || 'Server error'));
  }

  getAirportsByIsoCountry(country: String) : Observable<Airport[]> {
    return this.http.get(this.airportsByIsoCountryUrl + country)
                          .map((res:Response) => res.json())                         
                          .catch((error:any) => Observable.throw(error.json().error || 'Server error'));
  }

  getAirportsByAirportCode(airport: String) : Observable<Airport[]> {
    return this.http.get(this.airportsByAirportCodeUrl + airport)
                          .map((res:Response) => res.json())                         
                          .catch((error:any) => Observable.throw(error.json().error || 'Server error'));
  }

  getAirportsByAirportName(airport: String) : Observable<Airport[]> {
    return this.http.get(this.airportsByAirportNameUrl + airport)
                          .map((res:Response) => res.json())                         
                          .catch((error:any) => Observable.throw(error.json().error || 'Server error'));
  }

}