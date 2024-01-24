export class User {
  id : number;
  username : string;
  password : string;
  enabled : boolean;
  role : string;

  constructor(
    id : number = 1,
  username : string = '',
  password : string = '',
  enabled : boolean = false,
  role : string = ''
  ){
    this.id = id;
    this.username = username;
    this.enabled = enabled;
    this.password = password;
    this.role = role;
  }
}
