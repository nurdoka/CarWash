import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, ParamMap, Router } from '@angular/router';
import { StoreService } from '../../services/store.service';
import { CommentService } from '../../services/comment.service';
import { Comment } from '../../models/comment';
import { Store } from '../../models/store';

@Component({
  selector: 'app-comment-list',
  standalone: true,
  imports: [],
  templateUrl: './comment-list.component.html',
  styleUrl: './comment-list.component.css'

})
export class CommentListComponent implements OnInit{
  constructor(private activatedRoute: ActivatedRoute, 
    private router: Router, 
    private storeService: StoreService, 
    private commentService: CommentService) {}
    
  
  comments: Comment[] = [];
  storeID: number = 0;
  selected: Store = new Store();
  ngOnInit(): void {

    this.reload();

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
  }

  reload(): void {
 
    this.commentService.getCommentsByStoreId(this.storeID).subscribe({

    next: (comments) => {
      this.comments = comments;
    },
    error: (problem) => {
      console.error('storeService.index(): error loading stores:');
      console.error(problem);
    }
  });

  }
}