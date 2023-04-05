import { Component, EventEmitter, Output } from '@angular/core';
import { icons } from "../../core/const/image.const";

@Component({
  selector: 'app-sort',
  templateUrl: './sort.component.html',
  styleUrls: ['./sort.component.scss']
})
export class SortComponent {

  @Output()
  public OnSort: EventEmitter<Event>;

  public icon = icons;

  constructor() {
    this.OnSort = new EventEmitter<Event>();
  }

  public sort():void {
    this.OnSort.emit()
  }


}
