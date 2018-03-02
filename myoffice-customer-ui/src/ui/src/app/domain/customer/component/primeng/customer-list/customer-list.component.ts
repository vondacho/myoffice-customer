import {Component, OnInit} from '@angular/core';
import {CustomerClient} from '../../../service/client/customer-client.service';
import {CustomerState} from '../../../model/customer';
import {Pageable} from '../../../../../core/service/client/pageable';
import {FilterMetadata, LazyLoadEvent} from 'primeng/primeng';

interface Page {
    first: number;
    size: number;
    totalElements: number;
    totalPages: number;
}

interface Sort {
    field: string;
    order: number;
}

const DEFAULT_PAGE_SIZE = 10;

@Component({
    selector: 'myo-customer-list',
    templateUrl: './customer-list.component.html',
    styleUrls: ['./customer-list.component.css']
})
export class CustomerListComponent implements OnInit {

    elements: CustomerState[];
    selection: CustomerState[];
    page: Page;

    constructor(private client: CustomerClient) {
    }

    ngOnInit() {
        this.initElements();
    }

    loadLazy(event: LazyLoadEvent) {
        if (this.page.totalElements === 0) {
            this.loadCustomers(0, this.page.size);
        } else {
            this.page.size = event.rows;
            this.loadCustomers(
                Math.floor(event.first / this.page.size),
                this.page.size,
                event.sortField ? `${event.sortField},${event.sortOrder > 0 ? 'asc' : 'desc'}` : event.sortField,
                event.filters);
        }
    }

    private initElements() {
        this.elements = [];
        this.selection = [];
        this.page = {first: 0, size: DEFAULT_PAGE_SIZE, totalElements: 0, totalPages: 0};
    }

    private loadCustomers(page: number, size: number, sort?: string, filters?: { [s: string]: FilterMetadata; }) {
        (sort ?
            this.client.findAll$(page, size, sort, filters) :
            this.client.findAll$(page, size, null, filters))
            .subscribe(
                (p: Pageable<CustomerState>) => {
                    this.elements = p.content;
                    this.page.totalElements = p.totalElements;
                    this.page.totalPages = p.totalPages;
                },
                () => {
                    this.initElements();
                }
            );
    }
}
