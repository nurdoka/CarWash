import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Address } from '../models/address';
import { Observable, catchError, throwError } from 'rxjs';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class AddressService {

  private url = environment.baseUrl + 'api/';

  constructor(private http: HttpClient,private auth: AuthService) {}

  getHttpOptions() {
    let options = {
      headers: {
        Authorization: 'Basic ' + this.auth.getCredentials(),
        'X-Requested-With': 'XMLHttpRequest',
      },
    };
    return options;
  }

  getAddressByUsername(): Observable<Address>{

    return this.http
      .get<Address>(this.url + 'address', this.getHttpOptions())
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError(
            () => new Error( 'AddressService.getAddressByUsername: error retrieving address: ' + err )
          );
        })
      );
  }
}
