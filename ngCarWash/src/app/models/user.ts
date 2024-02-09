import { Address } from "./address";

export class User {
  id : number;
  firstName : string;
  lastName : string;
  email : string;
  username : string;
  password : string;
  enabled : boolean;
  role : string;
  address : Address;
  createDate : string;

  constructor(
    id : number = 0,
    firstName: string = '',
    lastName : string = '',
    email : string = '',
    username : string = '',
    password : string = '',
    enabled : boolean = false,
    role : string = '',
    address : Address = new Address(),
    createDate : string = ''
  ){
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.username = username;
    this.enabled = enabled;
    this.password = password;
    this.role = role;
    this.address = address;
    this.createDate = createDate;
  }
}


export interface User {
  id : number ;
  firstName: string;
  lastName : string;
  email : string;
  username : string;
  password : string;
  enabled : boolean;
  role : string;
  address : Address
}
