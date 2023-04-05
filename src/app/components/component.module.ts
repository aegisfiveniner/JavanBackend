import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ChipComponent } from './chip/chip.component';
import { ModalComponent } from './modal/modal.component';
import { SortComponent } from './sort/sort.component';
import { CardPanelComponent } from './card-panel/card-panel.component';
import { ProgressbarComponent } from './progressbar/progressbar.component';

@NgModule({
  declarations: [
    ChipComponent,
    ModalComponent,
    SortComponent,
    CardPanelComponent,
    ProgressbarComponent
  ],
  imports: [
    CommonModule
  ],
  exports: [
    ChipComponent,
    ModalComponent,
    SortComponent,
    CardPanelComponent,
    ProgressbarComponent
  ]

})
export class ComponentModule { }
