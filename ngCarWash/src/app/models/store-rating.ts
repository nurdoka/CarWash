import { User } from "./user";

export class StoreRating {
  rating: number;
  createDate: string;
  ratingComment: string;
  user: User;

  constructor(
    rating: number = 0,
    createDate: string = '',
    ratingComment: string = '',
    user: User = new User()
  ){
    this.rating = rating;
    this.createDate = createDate;
    this.ratingComment = ratingComment;
    this.user = user;
  }
}
