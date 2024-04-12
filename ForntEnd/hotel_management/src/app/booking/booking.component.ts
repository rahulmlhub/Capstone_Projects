import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';


@Component({
  selector: 'app-booking',
  templateUrl: './booking.component.html',
  styleUrls: ['./booking.component.css']
})
export class BookingComponent implements OnInit {
  bookingLists: any[] = [];
  booking: any = {};
  guests: any[] = []; // Assuming you have a list of guests
  hotels: any[] = []; // Assuming you have a list of hotels

  constructor(private http: HttpClient , private router:Router) { }

  ngOnInit(): void {
    // Fetch guests and hotels data when the component initializes
    this.fetchGuests();
    this.fetchHotels();
    this.fetchBookingData();
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

  fetchBookingData() {
    this.http.get<any[]>('http://localhost:8084/api/bookings')
      .subscribe(
        (data) => {

          console.log('Booking data fetched successfully:', data);
          this.bookingLists = data.map(booking => ({ ...booking, isEditing: false }));

        },
        (error) => {
          console.error('Error fetching booking data:', error);
        }
      );
      this.router.navigate(['booking-list']);

  }
  

  toggleEdit(booking: any) {
    booking.isEditing = true;
  }

  cancelEdit(booking: any) {
    booking.isEditing = false;
    // Optionally revert changes made in edit mode
  }

  updateBooking(booking: any) {
    // Implement update logic here, e.g., send PUT request to update the booking
    this.http.put(`http://localhost:8084/api/bookings/update/${booking.bookingId}`, booking)
      .subscribe(
        (updatedBooking) => {
          console.log('Booking updated successfully:', updatedBooking);
          // Optionally, you can update the existing booking object with the updated values
          Object.assign(booking, updatedBooking);
        },
        (error) => {
          console.error('Error updating booking:', error);
          // Optionally, display an error message to the user
        },
        () => {
          // Finally, set isEditing to false to switch back to view mode
          booking.isEditing = false;
        }
      );
  }
  

  deleteBooking(bookingId: string) {
    this.http.delete(`http://localhost:8084/api/bookings/delete/${bookingId}`)
      .subscribe(
        () => {
          console.log('Booking deleted successfully');
          // Filter out the deleted record from the bookingLists array
          this.bookingLists = this.bookingLists.filter(booking => booking.bookingId !== bookingId);
        },
        (error) => {
          console.error('Error deleting booking:', error);
        }
      );
  }

}
