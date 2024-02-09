import { FormsModule } from '@angular/forms';
import { AuthService } from './../../services/auth.service';
import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router, RouterLink } from '@angular/router';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [
    FormsModule,
    CommonModule,
    RouterLink
  ],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent{

  username:string = '';
  password:string = '';

  constructor(private auth:AuthService, private router:Router){}


  login():void{
    this.auth.login(this.username,this.password).subscribe({
      next: (LoggedInUser) => {
        this.router.navigateByUrl('profile');
      },
      error: (problem) => {
        console.log('LoginComponent.login(): Error logging in user: ' + problem);
      }
    });
  }
}


