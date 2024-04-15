import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { tap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class HotelListService {
  private baseUrl = 'http://localhost:8080/api/hotels/withFacilities'; // Change this to your backend URL

  constructor(private http: HttpClient) {}

  getHotels(): Observable<any[]> {
    // Retrieve token from localStorage or wherever you store it
    const token = localStorage.getItem('token');

    // Set the Authorization header with the token
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });

    return this.http.get<any[]>(this.baseUrl, { headers: headers })
      .pipe(
        tap(data => console.log('Data received:', data))
      );
  }
}
