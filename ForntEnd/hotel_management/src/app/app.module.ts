import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule } from '@angular/router';
import { AppRoutingModule } from './app-routing.module'; // Import your routing module
import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { FormGroup, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NavbarComponent } from './navbar/navbar.component';
import { GuestComponent } from './guest/guest.component';
import { BookingComponent } from './booking/booking.component';
import { ReviewComponent } from './review/review.component';
import { HotelListComponent } from './hotel-list/hotel-list.component';
import { NewRegistrationComponent } from './new-registration/new-registration.component';
import { AddReviewComponent } from './add-review/add-review.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatDialogModule } from '@angular/material/dialog';
import { HotelPopupComponent } from './hotel-popup/hotel-popup.component';
import { HttpClientModule } from '@angular/common/http';



@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    LoginComponent,
    NavbarComponent,
    GuestComponent,
    BookingComponent,
    ReviewComponent,
    HotelListComponent,
    NewRegistrationComponent,
    AddReviewComponent,
    HotelPopupComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    RouterModule, // Make sure RouterModule is imported here
    AppRoutingModule, // Import your routing module here
    ReactiveFormsModule,
    HttpClientModule,
    BrowserAnimationsModule,
    MatDialogModule
  ],
  providers: [
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
