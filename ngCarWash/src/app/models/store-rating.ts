import { Store } from "./store";
import { StoreRatingId } from "./store-rating-id";
import { User } from "./user";

export class StoreRating {
  id : StoreRatingId;
  rating: number;
  createDate: string;
  ratingComment: string;
  user: User;
  store: Store;

  constructor(
    id: StoreRatingId = new StoreRatingId(),
    rating: number = 0,
    createDate: string = '',
    ratingComment: string = '',
    user: User = new User(),
    store: Store = new Store()
  ){
    this.id = id;
    this.rating = rating;
    this.createDate = createDate;
    this.ratingComment = ratingComment;
    this.user = user;
    this.store = store;
  }
}
