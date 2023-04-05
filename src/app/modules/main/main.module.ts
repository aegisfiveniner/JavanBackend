import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';

import { ComponentModule } from '../../components/component.module';
import { PipeModule } from '../../shared/pipe/pipe.module';
import { InfoAssetsComponent } from './component/info-assets/info-assets.component';
import { InfoDiagramComponent } from './component/info-diagram/info-diagram.component';
import { InfoMaintenanceComponent } from './component/info-maintenance/info-maintenance.component';
import { InfoSparepartComponent } from './component/info-sparepart/info-sparepart.component';
import { MainRoutingModule } from './main-routing.module';
import { HomeDashboardComponent } from './page/home-dashboard/home-dashboard.component';

@NgModule({
  declarations: [
    HomeDashboardComponent,
    InfoSparepartComponent,
    InfoAssetsComponent,
    InfoMaintenanceComponent,
    InfoDiagramComponent,
  ],
  imports: [CommonModule, MainRoutingModule, ComponentModule, PipeModule],
})
export class MainModule {}
