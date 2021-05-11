import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Calculation } from '../model/calculation';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class HistoryService {

  calculations: Calculation[] = [];

  constructor(private http: HttpClient) { }

  getCalculationHistory(): Observable<Calculation[]> {
    return this.http.get<Calculation[]>(environment.calculationUrl + "/");
  }

  setHistory(calculations: Calculation[]): void {
    this.calculations = calculations;
  }

  addCalculation(calculation: Calculation) {
    if (this.calculations == null) this.calculations = [];
    this.calculations.push(calculation);
  }
}