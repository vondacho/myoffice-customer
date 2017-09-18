import {Injectable} from '@angular/core';
import {Observable} from 'rxjs/Observable';
import {Folder} from '../../model/folder';
import {HttpClient} from '@angular/common/http';

@Injectable()
export class FolderClient {

  constructor(private http: HttpClient) { }

  findAll(): Observable<Array<Folder>> {
    return this.http.get('/customer/api/v1/folders');
  }
}
