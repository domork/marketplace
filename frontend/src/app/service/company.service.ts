import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Company} from '../dto/company';
import {environment} from '../../environments/environment';
import {MessageService} from './message.service';

const baseUri = environment.backendUrl + '/company';

@Injectable({
  providedIn: 'root'
})
export class CompanyService {
  httpOptions = {headers: new HttpHeaders({'Content-Type': 'application/json'})};

  constructor(private http: HttpClient, private messageService: MessageService) {
  }

  getCompany(): Observable<Company[]> {
    this.messageService.add('CompanyService: fetched companies');
    return this.http.get<Company[]>(baseUri);
  }

  getCompanyById(id: number): Observable<Company> {
    this.messageService.add(`CompanyService: fetched company with id: ${id}`);
    return this.http.get<Company>(baseUri + '/' + id);
  }


}
