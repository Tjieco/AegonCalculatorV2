import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { Calculation } from 'src/app/model/calculation';
import { CalculateService } from 'src/app/services/calculate.service';
import { HistoryService } from 'src/app/services/history.service';

@Component({
  selector: 'app-history',
  templateUrl: './history.component.html',
  styleUrls: ['./history.component.css']
})
export class HistoryComponent implements OnInit {

  constructor(private historyService: HistoryService) { 
  }

  ngOnInit(): void {
    this.historyService.getCalculationHistory().subscribe(data => {
      this.historyService.setHistory(data);
    });
  }

  getCalculations(): Calculation[] {
    return this.historyService.calculations;
  }
}
