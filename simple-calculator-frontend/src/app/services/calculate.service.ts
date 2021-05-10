import { Injectable } from '@angular/core';
import { CalculationOperator } from '../components/calculate/calculate.component';
import { HttpClient } from '@angular/common/http';
import { Calculation } from '../model/calculation';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class CalculateService {
  calculate(first: number, second: number, operator: CalculationOperator): Observable<Calculation> {
    switch (operator) {
      case CalculationOperator.Add:
        return this.http.post<Calculation>(environment.calculationUrl + '/add', { first: first, second: second});
      case CalculationOperator.Subtract:
        return this.http.post<Calculation>(environment.calculationUrl + '/subtract', { first: first, second: second});
      case CalculationOperator.Multiply:
        return this.http.post<Calculation>(environment.calculationUrl + '/multiply', { first: first, second: second});
      case CalculationOperator.Divide:
        return this.http.post<Calculation>(environment.calculationUrl + '/divide', { first: first, second: second});
    }
  }

  constructor(private http: HttpClient) { }
}
