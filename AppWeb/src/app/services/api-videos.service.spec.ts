import { TestBed } from '@angular/core/testing';

import { ApiVideosService } from './api-videos.service';

describe('ApiVideosService', () => {
  let service: ApiVideosService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ApiVideosService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
