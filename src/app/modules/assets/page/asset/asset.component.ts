import { Component, OnDestroy, OnInit } from '@angular/core';
import { DBAsset } from "../../shared/data/db-asset";
import { IAsset } from "../../shared/model/asset.model";
import { ModalService } from "../../../../components/modal/service/modal.service";

@Component({
  selector: 'app-asset',
  templateUrl: './asset.component.html',
  styleUrls: ['./asset.component.scss']
})
export class AssetComponent implements OnInit, OnDestroy {

  public dataTable: IAsset[] = [];
  public isSort: boolean;

  constructor(
    private modal: ModalService
  ) {
  }

  ngOnInit(): void {
    this.getAssets();
  }

  ngOnDestroy(): void {
    this.modal.open = false;
  }

  private getAssets(): void {
    this.dataTable = DBAsset;
  }

  public search(keyword: string) {
    if(!keyword) {
      this.getAssets();
    } else {
      this.dataTable = this.dataTable.filter(data => data.assetName.toLowerCase().includes(keyword.toLowerCase()));
    }
  }

  public sortData(state: boolean): void {
    this.isSort = state;

    if(!this.isSort) {
      this.dataTable = this.dataTable.sort((asc: IAsset , desc: IAsset) => asc.id - desc.id);
    } else {
      this.dataTable = this.dataTable.sort((asc: IAsset , desc: IAsset) => desc.id - asc.id);
    }
  }

  public addAsset(): void {
    this.modal.open = true;
  }
}
