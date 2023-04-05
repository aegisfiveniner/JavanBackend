import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SearchFilterComponent } from './search-filter/search-filter.component';
import { FormsModule } from "@angular/forms";
import { ComponentModule } from "../component.module";



@NgModule({
  declarations: [
    SearchFilterComponent
  ],
  imports: [
    CommonModule,
    FormsModule,
    ComponentModule
  ],
  exports: [
    SearchFilterComponent,
  ]
})
export class SearchFilterModule { }
