import { Address } from "./address";
import { StoreRating } from "./store-rating";

export class Store {
  id : number;
  phone : string;
  email : string;
  name : string;
  createDate : string;
  lastUpdate : string;
  enabled : boolean;
  imageUrl : string;
  description : string;
  address : Address;
  storeRatings : StoreRating[];

  constructor(
    id : number = 0,
    phone : string = '',
    email : string = '',
    name : string = '',
    createDate : string = '',
    lastUpdate : string = '',
    enabled : boolean = false,
    imageUrl : string = '',
    description : string = '',
    address : Address = new Address(),
    storeRatings : StoreRating[] = []
  ){
    this.id = id;
    this.phone = phone;
    this.email = email;
    this.name = name;
    this.createDate = createDate;
    this.lastUpdate = lastUpdate;
    this.enabled  = enabled;
    this.imageUrl = imageUrl;
    this.description = description;
    this.address  = address;
    this.storeRatings = storeRatings;
  }
}
