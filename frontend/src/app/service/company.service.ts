import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable, of} from 'rxjs';
import {Company} from '../dto/company';
import {environment} from '../../environments/environment';
import {MessageService} from './message.service';
import {catchError, map, tap} from 'rxjs/operators';
import {CompanyExtended} from '../dto/company-extended';

const baseUri = environment.backendUrl + '/company';

@Injectable({
  providedIn: 'root'
})
export class CompanyService {
  httpOptions = {headers: new HttpHeaders({'Content-Type': 'application/json'})};
  parsedCompany: Company = {id: undefined, name: '2'};

  constructor(private http: HttpClient, private messageService: MessageService) {
  }

  getCompany(): Observable<Company[]> {
    return this.http.get<Company[]>(baseUri).pipe(
      tap(_ => this.log('fetched companies')), catchError(this.handleError<Company[]>('getCompany', [])));
  }

  getCompanyById(id: number): Observable<Company> {
    this.messageService.add(`CompanyService: fetched company with id: ${id}`);
    return this.http.get<Company>(baseUri + '/' + id);
  }

  addNewCompany(company: CompanyExtended): Observable<CompanyExtended> {
    return this.http.put<CompanyExtended>(baseUri, company, this.httpOptions);
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
