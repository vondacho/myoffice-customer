import { TestBed, inject } from '@angular/core/testing';

import { FolderClient } from './folder-client.service';
import {HttpClientTestingModule} from '@angular/common/http/testing';

describe('FolderClient', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [FolderClient]
    });
  });

  it('should be created', inject([FolderClient], (service: FolderClient) => {
    expect(service).toBeTruthy();
  }));
});
