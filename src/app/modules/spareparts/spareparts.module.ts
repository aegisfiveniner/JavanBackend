import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { SparepartsRoutingModule } from './spareparts-routing.module';
import { SparepartComponent } from './page/sparepart/sparepart.component';
import { ComponentModule } from "../../components/component.module";
import { SearchFilterModule } from "../../components/search-filter/search-filter.module";
import { SparepartTableComponent } from './component/sparepart-table/sparepart-table.component';


@NgModule({
  declarations: [
    SparepartComponent,
    SparepartTableComponent,
  ],
  imports: [
    CommonModule,
    SparepartsRoutingModule,
    SearchFilterModule,
    ComponentModule
  ]
})
export class SparepartsModule { }
