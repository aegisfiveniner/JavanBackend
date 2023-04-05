import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SparepartComponent } from "./page/sparepart/sparepart.component";

const routes: Routes = [
  {
    path: '',
    component: SparepartComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class SparepartsRoutingModule { }
