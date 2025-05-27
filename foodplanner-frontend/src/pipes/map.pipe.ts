import {Pipe, PipeTransform} from '@angular/core';
import {Ingredient} from '../models/ingredient.model';

@Pipe({
  name: 'map',
  standalone: true
})
export class MapPipe implements PipeTransform {
  transform<T extends Ingredient, K extends keyof T>(value: T[], propertyToExtract: K): T[K][] {
    if (!value) return [];
    return value.map(item => item[propertyToExtract]);
  }
}
