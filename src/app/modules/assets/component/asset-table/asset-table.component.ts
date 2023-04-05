import { Component, Input } from '@angular/core';
import { generateAssetStatus } from '../../shared/const/asset.config';
import { IAsset } from "../../shared/model/asset.model";

@Component({
  selector: 'app-asset-table',
  templateUrl: './asset-table.component.html'
})
export class AssetTableComponent {

  @Input()
  public dataTable: IAsset[] = [];

  public getStatus(status: string): string {
    return generateAssetStatus.get(status.toLowerCase())
  }


}
