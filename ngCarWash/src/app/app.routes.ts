import { Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { ProfileComponent } from './components/profile/profile.component';
import { HomeComponent } from './components/home/home.component';
import { ContactComponent } from './components/contact/contact.component';
import { AboutComponent } from './components/about/about.component';
import { RegisterComponent } from './components/register/register.component';
import { StoreListComponent } from './components/store-list/store-list.component';
<<<<<<< HEAD
import { NotFoundComponent } from './components/not-found/not-found.component';
import { CommentListComponent } from './components/comment-list/comment-list.component';
import { StoreRatingComponent } from './components/store-rating/store-rating.component';
=======
import { CommentListComponent } from './components/comment-list/comment-list.component';
>>>>>>> 08e5472139942f9b226f09fb2fd73f9a4e5906c3

export const routes: Routes = [
  { path: '', pathMatch: 'full', redirectTo: 'home' },
  { path: 'home', component: HomeComponent },
  { path: 'login', component: LoginComponent},
  { path: 'profile', component: ProfileComponent},
  { path: 'contact', component: ContactComponent},
  { path: 'register', component: RegisterComponent},
  { path: 'about', component: AboutComponent},
  { path: 'store', component: StoreListComponent},
<<<<<<< HEAD
  { path: 'comment/:storeId', component: CommentListComponent},
  { path: 'storeRating/:storeId', component: StoreRatingComponent},
  { path: '**', component: NotFoundComponent},

=======
  { path: 'comment/:storeId', component: CommentListComponent}
>>>>>>> 08e5472139942f9b226f09fb2fd73f9a4e5906c3
];


