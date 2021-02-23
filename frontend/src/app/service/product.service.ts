import {Injectable} from '@angular/core';
import {environment} from '../../environments/environment';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {MessageService} from './message.service';
import {Observable, of} from 'rxjs';
import {catchError, tap} from 'rxjs/operators';
import {Product} from '../dto/product';


const baseUri = environment.backendUrl + '/product';

@Injectable({
  providedIn: 'root'
})
export class ProductService {
  httpOptions = {headers: new HttpHeaders({'Content-Type': 'application/json'})};

  constructor(private http: HttpClient, private messageService: MessageService) {
  }

  getProducts(): Observable<Product[]> {
    return this.http.get<Product[]>(baseUri).pipe();
  }

  getProductById(id: number): Observable<Product> {
    this.messageService.add(`CompanyService: fetched product with id: ${id}`);
    return this.http.get<Product>(baseUri + '/' + id);
  }

  addNewProduct(product: Product): Observable<Product> {
    return this.http.put<Product>(baseUri, product, this.httpOptions);
  }

  deleteProduct(product: Product | number): Observable<Product> {
    const id = typeof product === 'number' ? product : product.id;
    const url = `${baseUri}/${id}`;
    return this.http.delete<Product>(url, this.httpOptions);
  }

  updateProduct(product: Product, id: number | undefined): Observable<Product> {
    const url = `${baseUri}/${id}`;
    product.id = id;
    return this.http.put<Product>(url, product, this.httpOptions);

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
