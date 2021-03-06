import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import {Observable, of} from 'rxjs';
import {Company} from '../dto/company';
import {environment} from '../../environments/environment';
import {MessageService} from './message.service';
import {catchError, map, tap} from 'rxjs/operators';
import {CompanyExtended} from '../dto/company-extended';
import {Product} from '../dto/product';
import {Country} from '@angular-material-extensions/select-country';

const baseUri = environment.backendUrl + '/company';

@Injectable({
  providedIn: 'root'
})
export class CompanyService {
  httpOptions = {headers: new HttpHeaders({'Content-Type': 'application/json'})};

  constructor(private http: HttpClient, private messageService: MessageService) {
  }

  getCompanies(): Observable<CompanyExtended[]> {
    return this.http.get<CompanyExtended[]>(baseUri).pipe(
      tap(_ => this.log('fetched companies')));
  }

  getCompanyById(id: number): Observable<CompanyExtended> {
    this.messageService.add(`CompanyService: fetched company with id: ${id}`);
    return this.http.get<CompanyExtended>(baseUri + '/' + id);
  }

  getCompaniesByName(name: string): Observable<CompanyExtended[]> {
    this.messageService.add(`CompanyService: fetched companies with name: ${name}`);
    const params = new HttpParams().set('name', name);
    const opt = {headers: new HttpHeaders({'Content-Type': 'application/json'}), params};

    return this.http.get<CompanyExtended[]>(baseUri, opt);
  }

  addNewCompany(company: CompanyExtended): Observable<CompanyExtended> {
    if (company.basedIn) {
      company.basedIn = (company.basedIn as Country).name;
    }
    return this.http.put<CompanyExtended>(baseUri, company, this.httpOptions);
  }

  deleteCompany(company: CompanyExtended | number): Observable<CompanyExtended> {

    const id = typeof company === 'number' ? company : company.id;
    const url = `${baseUri}/${id}`;
    return this.http.delete<CompanyExtended>(url, this.httpOptions);
  }

  updateCompany(company: CompanyExtended, id: number | undefined): Observable<CompanyExtended> {
    const url = `${baseUri}/${id}`;
    company.id = id;
    if (company.basedIn) {
    company.basedIn = (company.basedIn as Country).name;
    }
    return this.http.put<CompanyExtended>(url, company, this.httpOptions);

  }

  private handleError<T>(operation = 'operation', result?: T): any {
    return (error: any): Observable<T> => {
      console.error(error); // log to console instead

      this.log(`${operation} failed: ${error.message}`);

      // Let the app keep running by returning an empty result.
      return of(result as T);
    };
  }


  private log(message: string): void {
    this.messageService.add(`CompanyService: ${message}`);
  }
}
