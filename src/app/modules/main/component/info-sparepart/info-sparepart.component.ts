import { Component, OnInit } from '@angular/core';
import { SparepartStatusConfig } from "../../../spareparts/shared/class/sparepart-status-config.class";
import { ISparepart } from "../../../spareparts/shared/model/sparepart.model";
import { DBSparepartReview } from "../../shared/const/db-sparepart-review";

@Component({
  selector: 'app-info-sparepart',
  templateUrl: './info-sparepart.component.html',
  styleUrls: ['./info-sparepart.component.scss']
})
export class InfoSparepartComponent implements OnInit {

  public spareparts: ISparepart[];
  public statusConfig = new SparepartStatusConfig();
  public isSort: boolean;

  ngOnInit(): void {
    this.getSpareparts();
  }

  private getSpareparts(): void {
    this.spareparts = DBSparepartReview;
  }

  public sortData(state: boolean): void {
    this.isSort = state;

    if(!this.isSort) {
      this.spareparts = this.spareparts.sort((asc: ISparepart , desc: ISparepart) => asc.id - desc.id);
    } else {
      this.spareparts = this.spareparts.sort((asc: ISparepart , desc: ISparepart) => desc.id - asc.id);
    }
  }
}
