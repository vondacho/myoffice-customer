import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FolderSearchComponent } from './folder-search.component';

describe('FolderSearchComponent', () => {
  let component: FolderSearchComponent;
  let fixture: ComponentFixture<FolderSearchComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FolderSearchComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FolderSearchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
