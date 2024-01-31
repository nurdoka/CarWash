import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Store } from '../models/store';
import { Observable, catchError, throwError } from 'rxjs';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class StoreService {

  private url = environment.baseUrl + 'api/stores';

  constructor(
    private http: HttpClient,
    private auth: AuthService
    ) { }

    getHttpOptions() {
      let options = {
        headers: {
          Authorization: 'Basic ' + this.auth.getCredentials(),
          'X-Requested-With': 'XMLHttpRequest',
        },
      };
      return options;
    }

    index(): Observable<Store[]> {
      return this.http.get<Store[]>(this.url).pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError(
            () => new Error('StoreService.index(): error retrieving stores: ' + err)
          );
        })
      );
    }

    show(storeId : number): Observable<Store> {
      return this.http.get<Store>(this.url+"/"+storeId, this.getHttpOptions()).pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError(
            () => new Error('StoreService.index(): error retrieving stores: ' + err)
          );
        })
      );
    }
}
