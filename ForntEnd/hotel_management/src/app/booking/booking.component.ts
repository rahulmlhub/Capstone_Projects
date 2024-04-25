import { HttpClient, HttpHeaders } from '@angular/common/http';
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
  minDate: string;
  maxDate: string;
  datePipe: any;

  constructor(private http: HttpClient , private formBuilder : FormBuilder , private router:Router) {
    const today = new Date();
    const thirtyDaysLater = new Date();
    thirtyDaysLater.setDate(today.getDate() + 30);
    this.minDate = this.formatDate(today);
    this.maxDate = this.formatDate(thirtyDaysLater);
  }
  private formatDate(date: Date): string {
    const year = date.getFullYear();
    const month = (date.getMonth() + 1).toString().padStart(2, '0');
    const day = date.getDate().toString().padStart(2, '0');
    return `${year}-${month}-${day}`;
  }

  ngOnInit(): void {
    // Fetch guests and hotels data when the component initializes
    this.fetchGuests();
    this.fetchHotels();
    this.fetchBookingData();
  }
 
  saveBooking() {
    const token = localStorage.getItem('jwtToken');

    // Set the Authorization header with the token
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });
    // Assuming you have the data properly structured in your form
    const formData = {
      bookingId: this.booking.bookingId,
      guestId: this.booking.guestId,
      hotelId: this.booking.hotelId,
      numberOfGuest: this.booking.numberOfGuest,
      checkIn: this.booking.checkIn,
      checkOut: this.booking.checkOut
    };

    this.http.post<any>('http://localhost:8080/api/bookings/create', formData, {headers:headers})
      .subscribe((response) => {
        console.log('Booking saved successfully:', response);
        // Clear form after successful submission
        this.booking = {};
        this.fetchBookingData();
        // Navigate to booking component (optional)
        this.router.navigate(['booking']);

      }, (error) => {
        console.error('Error saving booking:', error);
      });
      this.router.navigate(['booking']);

  }

  fetchGuests() {

    const token = localStorage.getItem('jwtToken');

    // Set the Authorization header with the token
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });

    this.http.get<any[]>('http://localhost:8080/api/guests',{headers:headers})
      .subscribe((response) => {
        this.guests = response;
      }, (error) => {
        console.error('Error fetching guests:', error);
      });
  }

  fetchHotels() {
    const token = localStorage.getItem('jwtToken');

    // Set the Authorization header with the token
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });
    this.http.get<any[]>('http://localhost:8080/api/hotels',{headers:headers})
      .subscribe((response) => {
        this.hotels = response;
      }, (error) => {
        console.error('Error fetching hotels:', error);
      });
  }

  fetchBookingData() {
    const token = localStorage.getItem('jwtToken');

    // Set the Authorization header with the token
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });
    this.http.get<any[]>('http://localhost:8080/api/bookings',{headers:headers})
      .subscribe(
        (data) => {

          console.log('Booking data fetched successfully:', data);
          this.bookingLists = data.map(booking => ({ ...booking, isEditing: false }));

        },
        (error) => {
          console.error('Error fetching booking data:', error);
        }
      );
      // this.router.navigate(['/booking']);

  }
  

  toggleEdit(booking: any) {
    booking.isEditing = true;
  }

  cancelEdit(booking: any) {
    booking.isEditing = false;
    // Optionally revert changes made in edit mode
  }

  updateBooking(booking: any) {
    const token = localStorage.getItem('jwtToken');

    // Set the Authorization header with the token
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });
    // Implement update logic here, e.g., send PUT request to update the booking
    this.http.put(`http://localhost:8080/api/bookings/update/${booking.bookingId}`, booking,{headers:headers})
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
    const token = localStorage.getItem('jwtToken');

    // Set the Authorization header with the token
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });
    this.http.delete(`http://localhost:8080/api/bookings/delete/${bookingId}`,{headers:headers})
      .subscribe(
        () => {
          console.log('Booking deleted successfully');
          // Filter out the deleted record from the bookingLists array
          this.bookingLists = this.bookingLists.filter(booking => booking.bookingId !== bookingId);
          this.fetchBookingData();
          // Navigate to booking component (optional)
          this.router.navigate(['booking']);

        },
        (error) => {
          console.error('Error deleting booking:', error);
        }
      );
      this.router.navigate(['booking']);

  }

}
