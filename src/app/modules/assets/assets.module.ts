import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AssetsRoutingModule } from './assets-routing.module';
import { AssetComponent } from './page/asset/asset.component';
import { AssetTableComponent } from './component/asset-table/asset-table.component';
import { SearchFilterModule } from "../../components/search-filter/search-filter.module";
import { ComponentModule } from "../../components/component.module";
import { FormsModule } from "@angular/forms";
import { AddAssetDialogComponent } from './shared/dialog/add-asset-dialog/add-asset-dialog.component';
import { PipeModule } from "../../shared/pipe/pipe.module";


@NgModule({
  declarations: [
    AssetComponent,
    AssetTableComponent,
    AddAssetDialogComponent,
  ],
  imports: [
    CommonModule,
    AssetsRoutingModule,
    SearchFilterModule,
    ComponentModule,
    FormsModule,
    PipeModule
  ]
})
export class AssetsModule { }
