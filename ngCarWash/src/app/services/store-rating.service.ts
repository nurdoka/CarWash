import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { AuthService } from './auth.service';
import { Observable, catchError, throwError } from 'rxjs';
import { StoreRating } from '../models/store-rating';

@Injectable({
  providedIn: 'root'
})
export class StoreRatingService {

  private url = environment.baseUrl + 'api/storeRating';

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

  getStoreRating(storeId:number) : Observable<StoreRating>{
    return this.http.get<StoreRating>(this.url + '/' + storeId,this.getHttpOptions()).pipe(
      catchError((err:any) => {
        console.log(err);
        return throwError(
          () => new Error('StoreRatingService.getStoreRating: error retrieving storeRating: ' + err)
        );
      })
    );
  }

  getAllRatings(storeId:number) : Observable<StoreRating[]>{
    return this.http.get<StoreRating[]>(this.url + '/' + storeId +'/index',this.getHttpOptions()).pipe(
      catchError((err:any) => {
        console.log(err);
        return throwError(
          () => new Error('StoreRatingService.getAllRating: error retrieving storeRatings: ' + err)
        );
      })
    );
  }


  addRating(storeRating: StoreRating): Observable<StoreRating>{
    return this.http.post<StoreRating>(this.url, storeRating,this.getHttpOptions()).pipe(
      catchError((err:any) => {
        console.log(err);
        return throwError(
          () => new Error('StoreRatingService.addRating(): error adding rating: ' + err)
        )
      })
    );
  }

}
