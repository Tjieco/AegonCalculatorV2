import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HistoryService } from 'src/app/services/history.service';

import { HistoryComponent } from './history.component';

describe('HistoryComponent', () => {
  let component: HistoryComponent;
  let fixture: ComponentFixture<HistoryComponent>;
  let mockHistoryService: HistoryService;

  beforeEach(async(() => {
    mockHistoryService = jasmine.createSpyObj('historyService', {
      getCalculationHistory: of(),
      setHistory: of()
    });
    TestBed.configureTestingModule({
      declarations: [ HistoryComponent ],
      providers: [
        { provide: HistoryService, useValue: mockHistoryService}
      ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HistoryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
