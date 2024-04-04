import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule } from '@angular/router';
import { AppRoutingModule } from './app-routing.module'; // Import your routing module

import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { FormsModule } from '@angular/forms';
import { NavbarComponent } from './navbar/navbar.component';
import { HotelComponent } from './hotel/hotel.component';
import { GuestComponent } from './guest/guest.component';
import { FacilityComponent } from './facility/facility.component';
import { BookingComponent } from './booking/booking.component';
import { ReviewComponent } from './review/review.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    LoginComponent,
    NavbarComponent,
    HotelComponent,
    GuestComponent,
    FacilityComponent,
    BookingComponent,
    ReviewComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    RouterModule, // Make sure RouterModule is imported here
    AppRoutingModule // Import your routing module here
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
