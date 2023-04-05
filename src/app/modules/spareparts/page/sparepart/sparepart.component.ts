import { Component, OnInit } from '@angular/core';
import { ISparepart } from "../../shared/model/sparepart.model";
import { DBSparepart } from "../../shared/data/db-sparepart";
import { ModalService } from "../../../../components/modal/service/modal.service";

@Component({
  selector: 'app-sparepart',
  templateUrl: './sparepart.component.html',
  styleUrls: ['./sparepart.component.scss']
})
export class SparepartComponent implements OnInit {

  public spareparts: ISparepart[] = [];
  public isSort: boolean;

  constructor(private modal: ModalService) {}

  ngOnInit(): void {
    this.getSparepeart();
  }

  private getSparepeart(): void {
    this.spareparts = DBSparepart;
  }

  public search(keyword: string) {
    if(!keyword) {
      this.getSparepeart();
    } else {
      this.spareparts = this.spareparts.filter(data => data.sparepartName.toLowerCase().includes(keyword.toLowerCase()));
    }
  }

  public sortData(state: boolean): void {
    this.isSort = state;

    if(!this.isSort) {
      this.spareparts = this.spareparts.sort((asc: ISparepart , desc: ISparepart) => asc.id - desc.id);
    } else {
      this.spareparts = this.spareparts.sort((asc: ISparepart , desc: ISparepart) => desc.id - asc.id);
    }
  }

  public addAsset(): void {
    this.modal.open = true;
  }

}
