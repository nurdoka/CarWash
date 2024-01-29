import { User } from './../../models/user';
import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../services/auth.service';
import { AddressService } from '../../services/address.service';
import { Address } from '../../models/address';

@Component({
  selector: 'app-profile',
  standalone: true,
  imports: [],
  templateUrl: './profile.component.html',
  styleUrl: './profile.component.css'
})
export class ProfileComponent implements OnInit{

  loggedUser: User | null = null;
  address: Address | null = null;

  constructor(private auth: AuthService,private addressService: AddressService){}

  ngOnInit(): void {
    this.getUser();
    this.getAddress();
  }

  getUser():void{
    this.auth.getLoggedInUser().subscribe({
      next: (user) => {
        this.loggedUser = user;
        console.log(user);
      },
      error: (problem) => {
        console.log('Error getting user: ' + problem);
      }
    });
  }

  getAddress():void{
    this.addressService.getAddressByUsername().subscribe({
      next: (address) => {
        this.address = address;
        console.log(address);
      },
      error: (err) => {
        console.log('Error getting address: ' + err);
      }
    });
  }

}
