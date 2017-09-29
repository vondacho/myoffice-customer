import {Component, OnInit} from '@angular/core';
import {CustomerClient} from '../../../service/client/customer-client.service';
import {CustomerState} from '../../../model/customer';
import {Pageable} from '../../../../../core/service/client/pageable';
import 'rxjs/add/operator/toPromise';
import {Observable} from 'rxjs/Observable';
import {MenuItem, Message} from 'primeng/primeng';

@Component({
    selector: 'myo-customer-list',
    templateUrl: './customer-list.component.html',
    styleUrls: ['./customer-list.component.css']
})
export class CustomerListComponent implements OnInit {

    constructor(private client: CustomerClient) {

    }

    customers$: Observable<Pageable<CustomerState>>;
    selection: CustomerState[];
    items: MenuItem[];
    msgs: Message[] = [];

    ngOnInit() {
        this.customers$ = this.client.findAll();

        this.items = [
            {
                label: 'File',
                items: [{
                    label: 'New',
                    icon: 'fa-plus',
                    items: [
                        {label: 'Project'},
                        {label: 'Other'},
                    ]
                },
                    {label: 'Open'},
                    {label: 'Quit'}
                ]
            },
            {
                label: 'Edit',
                icon: 'fa-edit',
                items: [
                    {label: 'Undo', icon: 'fa-mail-forward'},
                    {label: 'Redo', icon: 'fa-mail-reply'}
                ]
            }
        ];
    }

    paginate(event) {
        console.log(JSON.stringify(event));
        // event.first = Index of the first record
        // event.rows = Number of rows to display in new page
        // event.page = Index of the new page
        // event.pageCount = Total number of pages
    }
}
