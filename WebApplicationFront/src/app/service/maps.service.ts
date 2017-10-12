import { Injectable } from '@angular/core';
import { Observer } from 'rxjs/Observer';
import { Observable } from 'rxjs/Observable';

@Injectable()
export class MapsService {

    getCurrentPosition(): Observable<Position> {
        return Observable.create((observer: Observer<Position>) => {
            navigator.geolocation.getCurrentPosition(
                (position: Position) => {
                    observer.next(position);
                    observer.complete();
                },
                (error: PositionError) => {
                    console.log('Geolocation service: ' + error.message);
                    observer.error(error);
                }
            );
        });
    }

    degreesToRadians(degrees: number): number {
        return degrees * Math.PI / 180;
    }
   
   

    distanceInKmBetweenEarthCoordinates(lat1, lon1, lat2, lon2): number {
        
        var earthRadiusKm = 6371;
        
        var dLat = this.degreesToRadians(lat2-lat1);        
        var dLon = this.degreesToRadians(lon2-lon1);

        lat1 = this.degreesToRadians(lat1);
        lat2 = this.degreesToRadians(lat2);

        var a = Math.sin(dLat/2) * Math.sin(dLat/2) + Math.sin(dLon/2) * Math.sin(dLon/2) * Math.cos(lat1) * Math.cos(lat2); 
        var c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a)); 
        
        return earthRadiusKm * c;

    }

}