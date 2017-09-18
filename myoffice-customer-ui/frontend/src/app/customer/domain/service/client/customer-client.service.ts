import { Injectable } from '@angular/core';
import {Observable} from 'rxjs/Observable';
import {Customer} from '../../model/customer';
import {HttpClient} from '@angular/common/http';

@Injectable()
export class CustomerClient {

  constructor(private http: HttpClient) { }

  findAll(): Observable<Array<Customer>> {
    return this.http.get('/customer/api/v1/customers');
  }
}
