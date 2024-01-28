import { User } from './../../models/user';
import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-profile',
  standalone: true,
  imports: [],
  templateUrl: './profile.component.html',
  styleUrl: './profile.component.css'
})
export class ProfileComponent implements OnInit{

  loggedUser: User | null = null;
  constructor(private auth: AuthService){}

  ngOnInit(): void {
    this.getUser();
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

}
