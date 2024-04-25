import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-add-review',
  templateUrl: './add-review.component.html',
  styleUrls: ['./add-review.component.css']
})
export class AddReviewComponent implements OnInit {

  reviewForm: FormGroup;
  bookingLists: any[] = [];

  constructor(private formBuilder: FormBuilder, private http: HttpClient, private router : Router) { 
    this.reviewForm = this.formBuilder.group({
      bookingId: ['', Validators.required],
      rating: ['', [Validators.required, Validators.min(1), Validators.max(5)]],
      comment: ['', Validators.required]
    });
  }
  
  ngOnInit(): void {
    this.reviewForm = this.formBuilder.group({
      bookingId: ['', Validators.required],
      rating: ['', [Validators.required, Validators.min(1), Validators.max(5)]],
      comment: ['', Validators.required]
    });

    // Fetch booking data when component initializes
    this.fetchBookingData();
  }

  saveReview() {
    if (this.reviewForm.invalid) {
      // Mark all fields as touched to trigger validation messages
      this.markFormGroupTouched(this.reviewForm);
      return;
    }

    const formData = this.reviewForm.value;
    const token = localStorage.getItem('jwtToken');
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });

    this.http.post<any>('http://localhost:8080/api/reviews/create', formData, { headers: headers })
      .subscribe(
        (response) => {
          console.log('Review saved successfully:', response);
          // Reset the form after successful submission
          this.reviewForm.reset();
        },
        (error) => {
          console.error('Error saving review:', error);
        }
      );
      this.router.navigate(['review'])
  }

  fetchBookingData() {
    const token = localStorage.getItem('jwtToken');
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });

    this.http.get<any[]>('http://localhost:8080/api/bookings', { headers: headers })
      .subscribe(
        (data) => {
          console.log('Booking data fetched successfully:', data);
          this.bookingLists = data;
        },
        (error) => {
          console.error('Error fetching booking data:', error);
        }
      );
  }

  markFormGroupTouched(formGroup: FormGroup) {
    (Object as any).values(formGroup.controls).forEach((control: FormGroup<any>) => {
      control.markAsTouched();

      if (control.controls) {
        this.markFormGroupTouched(control);
      }
    });
  }

}
