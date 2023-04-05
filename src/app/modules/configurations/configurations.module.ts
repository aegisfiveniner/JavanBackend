import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ConfigurationsRoutingModule } from './configurations-routing.module';
import { ConfigurationsComponent } from './page/configurations/configurations.component';
import { SearchFilterModule } from "../../components/search-filter/search-filter.module";
import { ConfigurationTableComponent } from './component/configuration-table/configuration-table.component';


@NgModule({
  declarations: [
    ConfigurationsComponent,
    ConfigurationTableComponent
  ],
  imports: [
    CommonModule,
    ConfigurationsRoutingModule,
    SearchFilterModule
  ]
})
export class ConfigurationsModule { }
