import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-review',
  templateUrl: './review.component.html',
  styleUrls: ['./review.component.css']
})
export class ReviewComponent implements OnInit {
  reviewsList: any[] = [];

  constructor(private http: HttpClient) { }

  ngOnInit(): void {
    this.fetchReviewData();
  }

  fetchReviewData() {
    this.http.get<any[]>('http://localhost:8085/api/reviews')
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
}
