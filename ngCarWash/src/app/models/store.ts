import { Address } from "./address";

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
    address : Address = new Address()
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
  }
}
