import { Component, Input } from '@angular/core';
import { ISparepart } from "../../shared/model/sparepart.model";
import { generateSparepartStatus } from "../../shared/const/sparepart.config";

@Component({
  selector: 'app-sparepart-table',
  templateUrl: './sparepart-table.component.html',
  styleUrls: ['./sparepart-table.component.scss']
})
export class SparepartTableComponent  {

  @Input()
  public dataTable: ISparepart[];

  public getStatus(quantity: number): string {
    return generateSparepartStatus(quantity);
  }

}
