import { Component, OnDestroy } from '@angular/core';
import { icons } from "../../core/const/image.const";
import { ModalService } from "./service/modal.service";

@Component({
  selector: 'app-modal',
  templateUrl: './modal.component.html',
  styleUrls: ['./modal.component.scss']
})
export class ModalComponent implements OnDestroy{

  public open = this.modal.open$;
  public closeIcon = icons.close;

  constructor(private modal: ModalService) {
  }

  ngOnDestroy(): void {
    this.modal.open = false;
  }

  public close(): void {
    this.modal.open = false;
  }

}
