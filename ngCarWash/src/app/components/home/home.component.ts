import { Component } from '@angular/core';
import { LoginComponent } from '../login/login.component';
import { RegisterComponent } from '../register/register.component';
import { AuthService } from '../../services/auth.service';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [
    LoginComponent,
    RegisterComponent,
    CommonModule,
    RouterLink,
    NgbModule
  ],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {

  constructor(private auth: AuthService){}

  checkLogin():boolean{
    return this.auth.checkLogin();
  }

  images = [
    'https://media.slidesgo.com/storage/86762/conversions/0-car-wash-business-plan-thumb.jpg',
    'https://lh3.googleusercontent.com/proxy/Sq7SJf5dlnepoENex7l69BIPCBxzIJtCfkZLQY4cO4Ey54RsgG3mmxiUlFOrzZbT54KeuTaDBYz7HLa3w6iNV8VjAF8d1c0XurNV',
    'https://assets-global.website-files.com/63a1be9079023a0a6c3babc2/63a1be9079023afd0f3bad75_photography_carousel-power-cleaning-equipment.webp'
  ];
}
