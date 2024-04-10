import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';

@Component({
  selector: 'app-booking-list',
  templateUrl: './booking-list.component.html',
  styleUrls: ['./booking-list.component.css']
})
export class BookingListComponent {

  bookingLists: any[] = [];

  constructor(private http: HttpClient) { }

  ngOnInit(): void {
    this.fetchBookingData();
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
