import { Component, Input } from '@angular/core';
import { IConfiguration } from "../../shared/model/configuration.model";

@Component({
  selector: 'app-configuration-table',
  templateUrl: './configuration-table.component.html',
  styleUrls: ['./configuration-table.component.scss']
})
export class ConfigurationTableComponent {

  @Input()
  public dataTable: IConfiguration[];

}
