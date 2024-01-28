export class User {
  id : number;
  firstName : string;
  lastName : string;
  username : string;
  password : string;
  enabled : boolean;
  role : string;

  constructor(
    id : number = 0,
    firstName: string = '',
    lastName : string = '',
    username : string = '',
    password : string = '',
    enabled : boolean = false,
    role : string = ''
  ){
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.username = username;
    this.enabled = enabled;
    this.password = password;
    this.role = role;
  }
}
