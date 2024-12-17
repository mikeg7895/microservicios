import { TestBed } from '@angular/core/testing';

import { SoapLogsService } from './soap-logs.service';

describe('SoapLogsService', () => {
  let service: SoapLogsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SoapLogsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
