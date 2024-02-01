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

  addComment(comment: Comment, id: number): Observable<Comment> {
    return this.http.post<Comment>(this.url+'/'+id, comment, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('CommentService.addComment(): error adding comment: ' + err)
        );
      })
    );
  }

  updateComment(comment: Comment, id: number): Observable<Comment>{
    return this.http.put<Comment>(this.url+'/'+id, comment, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('CommentService.updateComment(): error updating comment: ' + err)
        );
      })
    );
  }

  deleteComment(commentId: number): Observable<Comment>{
    console.log('CommentService.deleteComment(): ' + commentId);
    return this.http.delete<Comment>(this.url + '/' + commentId, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('CommentService.deleteComment(): error deleting comment: ' + err)
        )
      })
    );
  }
  
}
