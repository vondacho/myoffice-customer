import {Injectable} from '@angular/core';
import {CustomerState} from '../../model/customer';
import {HttpClient, HttpParams} from '@angular/common/http';
import {Pageable} from '../../../../core/service/client/pageable';
import {Observable} from 'rxjs/Observable';

@Injectable()
export class CustomerClient {

    static ENDPOINT_URL = '/api/customer/v1/customers';

    constructor(private http: HttpClient) {
    }

    private static encodeField(field: string): string {
        return field.replace('state.', '');
    }

    private static encodeFilter(filters: { [s: string]: any; }): string {
        let f = '';
        for (const field of Object.keys(filters)) {
            f += (f.length > 0 ? ',' : '') + `${this.encodeField(field)}:${filters[field].value}`;
        }
        return f;
    }

    findAll$(page?: number, size?: number, sort?: string, filters?: { [s: string]: any; }): Observable<Pageable<CustomerState>> {
        let query = new HttpParams();
        query = page ? query.append('page', page.toString()) : query;
        query = size ? query.append('size', size.toString()) : query;
        query = sort ? query.append('sort', this.encodeField(sort)) : query;
        query = filters ? query.append('filter', this.encodeFilter(filters)) : query;
        return this.http.get(CustomerClient.ENDPOINT_URL, {params: query});
    }
}
