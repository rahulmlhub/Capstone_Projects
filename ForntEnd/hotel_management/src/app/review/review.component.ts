import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-review',
  templateUrl: './review.component.html',
  styleUrls: ['./review.component.css']
})
export class ReviewComponent implements OnInit {
  reviewsList: any[] = [];
  // router: any;

  constructor(private http: HttpClient, private router: Router) { }

  ngOnInit(): void {
    this.fetchReviewData();
  }

  fetchReviewData() {
    const token = localStorage.getItem('jwtToken');

    // Set the Authorization header with the token
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });
    this.http.get<any[]>('http://localhost:8080/api/reviews',{headers:headers})
      .subscribe(
        (data) => {
          console.log('Review data fetched successfully:', data);
          this.reviewsList = data;
        },
        (error) => {
          console.error('Error fetching review data:', error);
        }
      );
  }
  redirectToEdit(review: any) {
    // Assuming you want to navigate to the "add-review" page with the review ID for editing
    // Example:
    this.router.navigate(['/add-review'], { state: { reviewId: review.reviewId } });
  }
}
