import { TestBed } from '@angular/core/testing';

import { RestaurantlistService } from './restaurantlist.service';

describe('RestaurantlistService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: RestaurantlistService = TestBed.get(RestaurantlistService);
    expect(service).toBeTruthy();
  });
});
