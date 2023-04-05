import { Component } from '@angular/core';
import { IUser } from "../shared/model/user.model";
import { DBUser } from "../shared/data/db-user";
import { Router } from "@angular/router";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {

  public username: string = '';
  public password: any = '';
  private user: IUser;

  constructor(private router: Router) { }

  public login(): void {
    setTimeout(() => {
      this.user = DBUser;
      console.log(this.username, this.password)
      if(this.username === this.user.fullname && this.password === this.user.password) {
        localStorage.setItem('user', JSON.stringify(this.user));
        this.router.navigate(['./home'])
      }
    }, 500)
  }

}
