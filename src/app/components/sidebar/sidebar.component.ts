import { Component, Input } from '@angular/core';
import { avatar, icons } from "../../core/const/image.const";
import { sidebarNavigations } from "./shared/const";
import { Router } from "@angular/router";

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.scss']
})
export class SidebarComponent {

  @Input()
  public activeTab: string;

  public photo = avatar;
  public icon = icons;
  public navigations = sidebarNavigations;

  constructor(private router: Router) {}

  public navigate(path: string): void {
    this.activeTab = path;
    this.router.navigate([path])
  }

  public logout(): void {
    localStorage.clear();
    this.router.navigate(['./auth']);
  }

  public home(): void {
    this.router.navigate(['./home'])
  }

}
