import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { GuestComponent } from './guest/guest.component';
import { BookingComponent } from './booking/booking.component';
import { ReviewComponent } from './review/review.component';
import { HotelListComponent } from './hotel-list/hotel-list.component';
import { BookingListComponent } from './booking-list/booking-list.component';

const routes: Routes = [
  { path: '', redirectTo: '/home', pathMatch: 'full' },
  { path: 'home', component: HomeComponent },
  { path: 'hotel', component: HotelListComponent },
  { path: 'guest', component: GuestComponent },
  { path: 'booking', component: BookingComponent },
  { path: 'review', component: ReviewComponent },
  { path: 'login', component: LoginComponent },
  { path: 'booking-list', component: BookingListComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
