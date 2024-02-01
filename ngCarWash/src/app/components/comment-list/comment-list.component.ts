import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, ParamMap, Router, RouterLink } from '@angular/router';

import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { StoreService } from '../../services/store.service';
import { CommentService } from '../../services/comment.service';
import { Comment } from '../../models/comment';
import { Store } from '../../models/store';
import { User } from './../../models/user';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-comment-list',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    RouterLink
  ],
  templateUrl: './comment-list.component.html',
  styleUrl: './comment-list.component.css'

})
export class CommentListComponent implements OnInit{
  constructor(
    private activatedRoute: ActivatedRoute,
    private router: Router,
    private storeService: StoreService,
    private commentService: CommentService,
    private auth: AuthService
  ) {}


  comments: Comment[] = [];
  newComment : Comment | null = null;
  selectedComment : Comment | null = null;
  storeID: number = 0;
  selected: Store = new Store();
  loggedUser: User = new User();

  ngOnInit(): void {

    this.activatedRoute.paramMap.subscribe(
      {
        next: (params: ParamMap) => {
          let storeIdStr = params.get('storeId');
          console.log("void 1" + storeIdStr);

          if(storeIdStr){
            let storeId = parseInt(storeIdStr);

            this.storeID = storeId;

            if( isNaN(storeId)){
              this.router.navigateByUrl("invalidStoreId 1");
            }else{
              this.storeService.show(storeId).subscribe(
                {
                  next: (store) => {
                    this.selected = store;
                  },
                  error: (nojoy) => {
                    this.router.navigateByUrl("invalidStoreId 2");
                  }
                });
            }
          }
        }
      }
    );
    this.reload();
  }

  reload(): void {

    this.commentService.getCommentsByStoreId(this.storeID).subscribe({
    next: (comments) => {
      console.log("--reload--");
      this.comments = comments;
      console.log(comments);
      console.log(this.storeID);
    },
    error: (problem) => {
      console.error('storeService.index(): error loading stores:');
      console.error(problem);
    }
  });
  }


  setAddComment():void{
    this.newComment = new Comment();

  }


  commentList():void{
    this.commentService.getCommentsByStoreId(this.storeID).subscribe({
      next: (comments) => {
        this.comments = comments;
        console.log('Retrieved comment list');
      },
      error : (err) => {
        console.log('Error retrieving commentList(): ' + err);
      }
    });
  }

  getUser():void{
    this.auth.getLoggedInUser().subscribe({
      next: (user) => {
        console.log("--get user--");
        console.log("user: " + user.username);
        this.loggedUser = user;

        console.log("loggedUser: " + this.loggedUser.username);
      },
      error: (problem) => {
        console.log('Error getting user: ' + problem);
      }
    });
  }


  displayComment(comment:Comment):void{
    this.selectedComment = comment;
  }


  addComment(comment: Comment):void{
    console.log(comment);
    this.commentService.addComment(comment, this.storeID).subscribe({
      next: (addedComment) => {
        console.log('Comment added');
        this.commentList();
        this.newComment = null;
      },
      error : (err) => {
        console.log('Error adding comment: ' + err);
      }
    });
  }


  updateComment(comment:Comment):void{
    this.commentService.updateComment(comment, comment.id).subscribe({
      next : (returnedComment) => {
        console.log('updating comment#: '+ comment.id);
        this.selectedComment = null;
      },
      error : (err) => {
        console.log('Error updating comment: ' + err);
      }
    });
  }

  deleteComment(commentId: number):void{
    console.log('delete: ' + commentId);
    this.commentService.deleteComment(commentId).subscribe({
      next : (returnedComment) => {
        console.log('deleting comment#: '+ commentId);
        this.selectedComment = null;
        console.log('error');
        this.commentList();
      }
    });
  }

}
