// comment.service.ts
import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Comment } from '../models/comment';
import { Observable, catchError, throwError } from 'rxjs';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class CommentService {

  private url = environment.baseUrl + 'api/comments';

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

  getCommentsByStoreId(storeId : number): Observable<Comment[]> {
    return this.http.get<Comment[]>(this.url+"/store/"+storeId, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('CommentService.GetStoreComments(): error retrieving comments: ' + err)
        );
      })
    );
  }




  /*getCommentsByStoreId(storeId: number): Observable<Comment[]> {
    const apiUrl = `${this.url}?storeId=${storeId}`;
    return this.http.get<Comment[]>(apiUrl, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('CommentService.getCommentsByStoreId(): error retrieving comments: ' + err)
        );
      })
    );
  }*/
}
