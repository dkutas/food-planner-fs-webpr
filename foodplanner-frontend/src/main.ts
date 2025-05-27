/// <reference types="@angular/localize" />

import {bootstrapApplication} from '@angular/platform-browser';
import {appConfig} from './app.config';
import {AppComponent} from './app.component';
import {registerLocaleData} from '@angular/common';
import en from '@angular/common/locales/en';

bootstrapApplication(AppComponent, appConfig).catch((err) =>
  console.error(err)
);
