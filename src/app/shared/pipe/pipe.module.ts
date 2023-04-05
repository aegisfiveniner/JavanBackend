import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { LocationPipe } from './location.pipe';



@NgModule({
  declarations: [
    LocationPipe
  ],
  imports: [
    CommonModule
  ],
  exports: [
    LocationPipe
  ]
})
export class PipeModule { }
