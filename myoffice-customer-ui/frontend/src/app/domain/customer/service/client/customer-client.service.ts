import {Injectable} from '@angular/core';
import {CustomerState} from '../../model/customer';
import {HttpClient} from '@angular/common/http';
import 'rxjs/add/operator/map';
import {Pageable} from '../../../../core/service/client/pageable';
import {Observable} from 'rxjs/Observable';

@Injectable()
export class CustomerClient {

    constructor(private http: HttpClient) {
    }

    findAll(): Observable<Pageable<CustomerState>> {
        return this.http.get('/api/customer/v1/customers');
    }
}
