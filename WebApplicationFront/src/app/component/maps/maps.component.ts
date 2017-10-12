import { Component, Input, SimpleChanges } from '@angular/core';
import { MapsService } from './../../service/maps.service';

import { Airport } from './../../model/airport';

@Component({
  selector: 'maps-component',
  templateUrl: 'maps-component.html',
  styleUrls: ['maps-component.css'],
})
export class MapsComponent {

  @Input() airport: Airport;

  userLatitude: number;
  userLongitude: number;

  airportLatitude: number;
  airportLongitude: number;

  distanceBeetwenAB: number;
  warning: boolean;
  message: string;

  constructor(private mapsService: MapsService) {  }

  ngOnInit() : void {      
    this.getCurrentLocalizaction();  
    this.setAirportCordination();
  }
  
  ngOnChanges(changes: SimpleChanges): void {
    if (changes['airport']) {
      this.setAirportCordination();
      this.calculateDistance();
    }
  }

  setAirportCordination(): void {
    this.airportLatitude = this.convertDMSToDecimal(this.airport.latitudeNpeerS, Number(this.airport.latitudeDegree), Number(this.airport.latitudeMinute), Number(this.airport.latitudeSecond));
    this.airportLongitude = this.convertDMSToDecimal(this.airport.longitudeEperW, Number(this.airport.longitudeDegree), Number(this.airport.longitudeMinute), Number(this.airport.longitudeSeconds));        
  }  

  calculateDistance(): void {
    this.distanceBeetwenAB = this.mapsService.distanceInKmBetweenEarthCoordinates(Number(this.userLatitude), Number(this.userLongitude), Number(this.airportLatitude), Number(this.airportLongitude));
  }

  getCurrentLocalizaction(): void {
    this.warning = false;
    this.message = "";

    if (navigator.geolocation) {
      this.mapsService.getCurrentPosition().subscribe(
                (position: Position) => {
                  this.userLatitude = position.coords.latitude;                 
                  this.userLongitude = position.coords.longitude;
                  this.calculateDistance(); 
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

  convertDMSToDecimal(direction: string, degree: number, minute: number, second: number): number{
    if(direction == "W" || direction == "S") {
      return -Math.abs(degree + (minute/60) + (second/3600));      
    } else {
      return degree + (minute/60) + (second/3600);
    }  
  }

}