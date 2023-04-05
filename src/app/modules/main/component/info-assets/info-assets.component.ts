import { Component, OnInit } from '@angular/core';
import { IAsset } from "../../../assets/shared/model/asset.model";
import { DBAssetReview } from "../../shared/const/db-asset-review";
import { AssetConfigStatus } from "../../../assets/shared/class/asset-config-status.config";

@Component({
  selector: 'app-info-assets',
  templateUrl: './info-assets.component.html',
  styleUrls: ['./info-assets.component.scss']
})
export class InfoAssetsComponent implements OnInit {

  public assets: IAsset[];
  public statusConfig = new AssetConfigStatus();
  public isSort: boolean;

  ngOnInit(): void {
    this.getAssets();
  }

  private getAssets(): void {
    this.assets = DBAssetReview;
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
