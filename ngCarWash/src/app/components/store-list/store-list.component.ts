import { Component, OnInit } from '@angular/core';
import { Store } from '../../models/store';
import { StoreService } from '../../services/store.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterLink } from '@angular/router';
import { NgbRatingModule } from '@ng-bootstrap/ng-bootstrap';
import { StoreRatingService } from '../../services/store-rating.service';
import { StoreRating } from '../../models/store-rating';


@Component({
  selector: 'app-store-list',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    RouterLink,
    NgbRatingModule
  ],
  templateUrl: './store-list.component.html',
  styleUrls: ['./store-list.component.css']  // Fix the property name here
})
export class StoreListComponent implements OnInit {
  stores: Store[] = [];
  storeRatings: StoreRating[] = [];
  rating: number = 0;

  constructor(
    private storeService: StoreService,
    private storeRatingService: StoreRatingService
  ) {}

  ngOnInit(): void {
    this.reload();
  }

  reload(): void {
    this.storeService.index().subscribe({
      next: (stores) => {
        this.stores = stores;
      },
      error: (problem) => {
        console.error('storeService.index(): error loading stores:');
        console.error(problem);
      }
    });
  }

  getStoreRating(storeRating: StoreRating[]):number{
    let num = 0;
    for(let i = 0; i < storeRating.length; i++){
      num+= storeRating[i].rating;
    }
    this.rating = num/storeRating.length;
    return this.rating;
  }
}
