import { User } from "./user";

export class Vehicle {
  id : number;
  make : string;
  model : string;
  year : number;
  licensePlate : string;
  createDate : string;
  lastUpdate : string;
  enabled : boolean;
  color : string;
  user : User;

  constructor(
    id : number = 0,
    make : string = '',
    model : string = '',
    year : number = 0,
    licensePlate : string = '',
    createDate : string = '',
    lastUpdate : string = '',
    enabled : boolean = false,
    color : string = '',
    user : User = new User()
  ){
    this.id = id;
    this.make = make;
    this.model = model;
    this.year = year;
    this.licensePlate = licensePlate;
    this.createDate = createDate;
    this.lastUpdate = lastUpdate;
    this.enabled = enabled;
    this.color = color;
    this.user = user;
  }
}
