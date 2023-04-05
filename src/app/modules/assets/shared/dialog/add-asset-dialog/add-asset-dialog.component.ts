import { Component } from '@angular/core';
import { image } from "../../../../../core/const/image.const";

@Component({
  selector: 'app-add-asset-dialog',
  templateUrl: './add-asset-dialog.component.html',
  styleUrls: ['./add-asset-dialog.component.scss']
})
export class AddAssetDialogComponent  {

  public image = image;
}
