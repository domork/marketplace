import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable, of} from 'rxjs';
import {Company} from '../dto/company';
import {environment} from '../../environments/environment';
import {catchError} from 'rxjs/operators';

const baseUri = environment.backendUrl + '/company';

@Injectable({
  providedIn: 'root'
})
export class CompanyService {
  httpOptions = {headers: new HttpHeaders({'Content-Type': 'application/json'})};

  constructor(private http: HttpClient) {
  }

  getCompany(): Observable<number> {
    return this.http.get<number>(baseUri);
  }
}
