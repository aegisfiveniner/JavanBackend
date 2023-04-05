import { Component, OnInit } from '@angular/core';
import { DBConfigurations } from "../../shared/data/db-configuration";
import { IConfiguration } from "../../shared/model/configuration.model";


@Component({
  selector: 'app-configurations',
  templateUrl: './configurations.component.html',
  styleUrls: ['./configurations.component.scss']
})
export class ConfigurationsComponent implements OnInit {

  public configurations: IConfiguration[];
  public isSort: boolean;


  ngOnInit(): void {
    this.getConfigurations();
  }

  private getConfigurations(): void {
    this.configurations = DBConfigurations;
  }

  public search(keyword: string) {
    if(!keyword) {
      this.getConfigurations();
    } else {
      this.configurations = this.configurations.filter(data => data.configName.toLowerCase().includes(keyword.toLowerCase()));
    }
  }

  public sortData(state: boolean): void {
    this.isSort = state;

    if(!this.isSort) {
      this.configurations = this.configurations.sort((asc: IConfiguration , desc: IConfiguration) => asc.id - desc.id);
    } else {
      this.configurations = this.configurations.sort((asc: IConfiguration , desc: IConfiguration) => desc.id - asc.id);
    }
  }
}
