import { Injectable } from '@angular/core';
import { BehaviorSubject } from "rxjs";


@Injectable({
  providedIn: 'root'
})
export class ModalService {
  public openModal: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false);
  public open$ = this.openModal.asObservable();

  set open(value: boolean) {
    this.openModal.next(value)
  }

  get open(): boolean {
    return this.openModal.value;
  }
}
