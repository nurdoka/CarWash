import { Component, OnInit } from '@angular/core';
import { StoreRating } from '../../models/store-rating';
import { ActivatedRoute, ParamMap, Router } from '@angular/router';
import { StoreService } from '../../services/store.service';
import { CommentService } from '../../services/comment.service';
import { StoreRatingService } from '../../services/store-rating.service';
import { AuthService } from '../../services/auth.service';
import { Store } from '../../models/store';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-store-rating',
  standalone: true,
  imports: [
    FormsModule,
    CommonModule
  ],
  templateUrl: './store-rating.component.html',
  styleUrl: './store-rating.component.css'
})
export class StoreRatingComponent implements OnInit{

  constructor(
    private activatedRoute: ActivatedRoute,
    private router: Router,
    private storeService: StoreService,
    private commentService: CommentService,
    private storeRatingService: StoreRatingService,
    private auth: AuthService
  ) {}


  storeId:number = 0;
  storeRating: StoreRating = new StoreRating();
  storeRatings: StoreRating[] = [];
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

            this.storeId = storeId;

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
    this.getAllRatingsByStoreId();
    this.getStoreRatingByStoreId();
  }

  getStoreRatingByStoreId():void{
    this.storeRatingService.getStoreRating(this.storeId).subscribe({
      next : (storeRating) => {
        console.log(storeRating);
        this.storeRating = storeRating;
      },
      error : (err) => {
        console.log('storeRatingService.getStoreRating(): error retrieving store rating: ' + err);
      }
    });
  }

  getAllRatingsByStoreId():void{
    this.storeRatingService.getAllRatings(this.storeId).subscribe({
      next : (ratings) => {
        console.log(ratings);
        this.storeRatings = ratings;
      },
      error : (err) => {
        console.log('storeRatingService.getAllRatings(): error retrieving store ratings: ' + err);
      }
    });
  }

  addStoreRating():void{
    this.storeRating.store = this.selected;

    console.log(this.storeRating);
    this.storeRatingService.addRating(this.storeRating).subscribe({
      next : (rating) => {
        console.log(rating);
        this.storeRating = rating;
        this.getAllRatingsByStoreId();
      },
      error : (err) => {
        console.log('storeRatingService.addRating(): error adding store rating: ' + err);
      }
    });
  }

}
