import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';
import { AuthService } from '../../services/auth.service';
import { LogoutComponent } from '../logout/logout.component';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-navigation',
  standalone: true,
  imports: [
    RouterLink,
    CommonModule,
    LogoutComponent
  ],
  templateUrl: './navigation.component.html',
  styleUrl: './navigation.component.css'
})
export class NavigationComponent {
  constructor(private auth:AuthService){}

  checkLogin():boolean{
    return this.auth.checkLogin();
  }
}
