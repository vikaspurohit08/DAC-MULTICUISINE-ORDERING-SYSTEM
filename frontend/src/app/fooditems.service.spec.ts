import { TestBed } from '@angular/core/testing';

import { FooditemsService } from './fooditems.service';

describe('FooditemsService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: FooditemsService = TestBed.get(FooditemsService);
    expect(service).toBeTruthy();
  });
});
