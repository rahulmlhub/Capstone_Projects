import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-booking',
  templateUrl: './booking.component.html',
  styleUrls: ['./booking.component.css']
})
export class BookingComponent implements OnInit {

  booking: any = {};
  guests: any[] = []; // Assuming you have a list of guests
  hotels: any[] = []; // Assuming you have a list of hotels

  constructor(private http: HttpClient , private router:Router) { }

  ngOnInit(): void {
    // Fetch guests and hotels data when the component initializes
    this.fetchGuests();
    this.fetchHotels();
  }

  saveBooking() {
    // Assuming you have the data properly structured in your form
    const formData = {
      bookingId: this.booking.bookingId,
      guestId: this.booking.guestId,
      hotelId: this.booking.hotelId,
      numberOfGuest: this.booking.numberOfGuest,
      checkIn: this.booking.checkIn,
      checkOut: this.booking.checkOut
    };

    this.http.post<any>('http://localhost:8084/api/bookings/create', formData)
      .subscribe((response) => {
        console.log('Booking saved successfully:', response);
        // Clear form after successful submission
        this.booking = {};
        this.router.navigate(['booking-list']);
      }, (error) => {
        console.error('Error saving booking:', error);
      });
  }

  fetchGuests() {
    this.http.get<any[]>('http://localhost:8082/api/guests')
      .subscribe((response) => {
        this.guests = response;
      }, (error) => {
        console.error('Error fetching guests:', error);
      });
  }

  fetchHotels() {
    this.http.get<any[]>('http://localhost:8081/api/hotels')
      .subscribe((response) => {
        this.hotels = response;
      }, (error) => {
        console.error('Error fetching hotels:', error);
      });
  }
}
