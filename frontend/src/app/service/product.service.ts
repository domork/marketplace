import {Injectable} from '@angular/core';
import {environment} from '../../environments/environment';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {MessageService} from './message.service';
import {Observable, of} from "rxjs";
import {CompanyExtended} from "../dto/company-extended";
import {catchError, tap} from "rxjs/operators";
import {Product} from "../dto/product";


const baseUri = environment.backendUrl + '/product';

@Injectable({
  providedIn: 'root'
})
export class ProductService {
  httpOptions = {headers: new HttpHeaders({'Content-Type': 'application/json'})};

  constructor(private http: HttpClient, private messageService: MessageService) {
  }

  getProducts(): Observable<Product[]> {
    return this.http.get<Product[]>(baseUri).pipe(
      (catchError(this.handleError<Product[]>('getProduct', []))));
  }

  getProductById(id: number): Observable<Product> {
    this.messageService.add(`CompanyService: fetched product with id: ${id}`);
    return this.http.get<Product>(baseUri + '/' + id);
  }

  addNewProduct(product: Product): Observable<Product> {
    return this.http.put<Product>(baseUri, product, this.httpOptions);
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
