import { Component, OnInit } from '@angular/core';
import { IAsset } from "../../../assets/shared/model/asset.model";
import { DBAssetInactiveReview } from "../../shared/const/db-inactive-review";

@Component({
  selector: 'app-info-maintenance',
  templateUrl: './info-maintenance.component.html',
  styleUrls: ['./info-maintenance.component.scss']
})
export class InfoMaintenanceComponent implements OnInit {

  public assets: IAsset[];
  public isSort: boolean;

  ngOnInit(): void {
    this.getAssets();
  }

  private getAssets(): void {
    this.assets = DBAssetInactiveReview;
  }

  public getBg(progress: number) : string {
    let bg: string;

    if (progress > 50) {
      bg = '#E30E0E'
    } else if (progress === 50 ) {
      bg = '#E4AE25'
    } else {
      bg = '#37B744'
    }

    return bg
  }

  public sortData(state: boolean): void {
    this.isSort = state;

    if (!this.isSort) {
      this.assets = this.assets.sort((asc: IAsset, desc: IAsset) => asc.id - desc.id);
    } else {
      this.assets = this.assets.sort((asc: IAsset, desc: IAsset) => desc.id - asc.id);
    }
  }


}
