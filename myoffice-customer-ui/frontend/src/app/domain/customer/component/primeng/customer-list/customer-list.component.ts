import {Component, OnInit} from '@angular/core';
import {CustomerClient} from '../../../service/client/customer-client.service';
import {Observable} from 'rxjs/Observable';
import {CustomerState} from '../../../model/customer';
import {Pageable} from '../../../../../core/service/client/pageable';

@Component({
    selector: 'myo-customer-list',
    templateUrl: './customer-list.component.html',
    styleUrls: ['./customer-list.component.css']
})
export class CustomerListComponent implements OnInit {

    constructor(private client: CustomerClient) {
    }

    customers$: Observable<Pageable<CustomerState>>;

    ngOnInit() {
        this.customers$ = this.client.findAll();
    }

}
