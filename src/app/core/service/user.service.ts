import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  public isLogin(): boolean {
    const user = localStorage.getItem('user');
    return !!user;
  }
}
