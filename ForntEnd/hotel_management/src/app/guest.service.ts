// guest.service.ts
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
// import { GuestDTO } from './guest.model';

@Injectable({
  providedIn: 'root'
})
export class GuestService {
  private baseUrl = 'http://localhost:8080/api/guests'; // Change this to your backend URL

  constructor(private http: HttpClient) {}

  getGuests(): Observable<any[]> {
    const token = localStorage.getItem('jwtToken');

    // Set the Authorization header with the token
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });
    return this.http.get<any[]>(this.baseUrl,{headers:headers});
  }
}
