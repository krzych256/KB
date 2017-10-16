import { Component } from '@angular/core';

@Component({
  selector: 'view-component',
  styles: [`
    .app {
      display: block;
      text-align: center;
      padding: 25px;
      background: #f5f5f5;
    }
  `],
  template: `
    <div class="app">
      Parent: {{ myCount }}
      <counter
        [count]="myCount"
        (change)="countChange($event)">
      </counter>
    </div>
  `
})
export class ViewComponent {

  myCount: number = 10;
  
  countChange(event) {
    this.myCount = event;
  }
}