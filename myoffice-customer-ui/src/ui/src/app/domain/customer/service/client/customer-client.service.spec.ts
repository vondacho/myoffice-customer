import {inject, TestBed} from '@angular/core/testing';

import {CustomerClient} from './customer-client.service';
import {HttpClientTestingModule} from '@angular/common/http/testing';

describe('CustomerClient', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [CustomerClient]
    });
  });

  it('should be created', inject([CustomerClient], (service: CustomerClient) => {
    expect(service).toBeTruthy();
  }));
});
