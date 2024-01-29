import { User } from './../../models/user';
import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../services/auth.service';
import { AddressService } from '../../services/address.service';
import { Address } from '../../models/address';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { UserService } from '../../services/user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-profile',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule
  ],
  templateUrl: './profile.component.html',
  styleUrl: './profile.component.css'
})
export class ProfileComponent implements OnInit{

  loggedUser: User | null = null;
  address: Address | null = null;
  selected: User | null = null;

  constructor(
    private auth: AuthService,
    private addressService: AddressService,
    private userServ: UserService,
    private router: Router
    ){}

  ngOnInit(): void {
    this.getUser();
  }

  editUser():void{
    this.selected = new User();
  }

  updateUser(user: User):void{
    this.userServ.updateUser(user).subscribe({
      next: (updatedUser) => {
        console.log('Updated user');
        this.loggedUser = updatedUser;
        this.selected = null;
      },
      error: (err) => {
        console.log('Error updating user' + err);
      }
    });
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
