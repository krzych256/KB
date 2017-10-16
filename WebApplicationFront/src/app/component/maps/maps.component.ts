import { Component, Input } from '@angular/core';

import { GeographicCoordination } from './../../model/geographicCoordination';

@Component({
  selector: 'maps-component',
  templateUrl: 'maps-component.html',
  styleUrls: ['maps-component.css'],
})
export class MapsComponent {

  @Input() coordinationA: GeographicCoordination;

  @Input() coordinationB: GeographicCoordination;

  @Input() isCoordinationBExist: boolean = false;

  @Input() isPolylineExist: boolean = false;
  
}