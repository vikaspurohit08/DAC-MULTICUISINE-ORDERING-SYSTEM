import { TestBed } from '@angular/core/testing';

import { EmtrService } from './emtr.service';

describe('EmtrService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: EmtrService = TestBed.get(EmtrService);
    expect(service).toBeTruthy();
  });
});
