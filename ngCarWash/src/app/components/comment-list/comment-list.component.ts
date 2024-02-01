import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, ParamMap, Router, RouterLink } from '@angular/router';
import { StoreService } from '../../services/store.service';
import { CommentService } from '../../services/comment.service';
import { Comment } from '../../models/comment';
import { Store } from '../../models/store';
import { CommonModule } from '@angular/common';
import { StoreRatingService } from '../../services/store-rating.service';
import { StoreRating } from '../../models/store-rating';
import { FormsModule } from '@angular/forms';
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
    private storeRatingService: StoreRatingService,
    private auth: AuthService
  ) {}


  comments: Comment[] = [];
  storeID: number = 0;
  selected: Store = new Store();

  ngOnInit(): void {

    this.activatedRoute.paramMap.subscribe(
      {
        next: (params: ParamMap) => {
          let storeIdStr = params.get('storeId');
          console.log("void 1" + storeIdStr);

          if(storeIdStr){
            let storeId = parseInt(storeIdStr);
            console.log();

            this.storeID = storeId;

            if( isNaN(storeId)){
              this.router.navigateByUrl("invalidStoreId1");
            }else{
              this.storeService.show(storeId).subscribe(
                {
                  next: (store) => {
                    this.selected = store;
                  },
                  error: (nojoy) => {
                    this.router.navigateByUrl("invalidStoreId2");
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



}
