import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'location'
})
export class LocationPipe implements PipeTransform {

  transform(value: number): string {
    let location: string;
    if(value === 2) {
      location = 'External'
    } else {
      location = 'Internal'
    }
    return location;
  }

}
