import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {CustomerListComponent} from './customer-list.component';
import {CustomerClient} from '../../../service/client/customer-client.service';
import {HttpClientTestingModule} from '@angular/common/http/testing';

describe('CustomerListComponent', () => {
    let component: CustomerListComponent;
    let fixture: ComponentFixture<CustomerListComponent>;

    beforeEach(async(() => {
        TestBed.configureTestingModule({
            imports: [
                HttpClientTestingModule
            ],
            declarations: [
                CustomerListComponent
            ],
            providers: [
                CustomerClient
            ]
        }).compileComponents();
    }));

    beforeEach(() => {
        fixture = TestBed.createComponent(CustomerListComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
