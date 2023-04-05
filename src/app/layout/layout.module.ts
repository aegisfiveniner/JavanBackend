import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { LayoutRoutingModule } from './layout-routing.module';
import { LayoutComponent } from './layout/layout.component';
import { SidebarModule } from "../components/sidebar/sidebar.module";
import { BreadcrumbModule } from "../shared/static/breadcrumb/breadcrumb.module";


@NgModule({
  declarations: [
    LayoutComponent
  ],
  imports: [
    CommonModule,
    LayoutRoutingModule,
    SidebarModule,
    BreadcrumbModule
  ]
})
export class LayoutModule { }
