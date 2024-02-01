
export class StoreRatingId {
  userId: number;
  storeId: number;

  constructor(
    userId: number = 0,
    storeId: number = 0
  ){
    this.userId = userId;
    this.storeId = storeId;
  }
}
