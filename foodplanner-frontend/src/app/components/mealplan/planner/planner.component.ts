import {Component} from '@angular/core';

import {NzCalendarMode, NzCalendarModule} from 'ng-zorro-antd/calendar';
import {FormsModule} from '@angular/forms';
import {en_US, NzI18nService} from 'ng-zorro-antd/i18n';

@Component({
  selector: 'app-planner',
  imports: [FormsModule, NzCalendarModule],
  templateUrl: './planner.component.html',
  styleUrls: ['./planner.component.less']
})
export class PlannerComponent {
  constructor(private i18n: NzI18nService) {
  }

  date = new Date();
  mode: NzCalendarMode = 'month';

  ngOnInit(): void {
    this.i18n.setLocale(en_US);
  }

  panelChange(change: { date: Date; mode: string }): void {
    console.log(change.date, change.mode);
  }

}
