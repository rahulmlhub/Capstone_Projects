// guest.service.ts
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
// import { GuestDTO } from './guest.model';

@Injectable({
  providedIn: 'root'
})
export class GuestService {
  private baseUrl = 'http://localhost:8082/api/guests'; // Change this to your backend URL

  constructor(private http: HttpClient) {}

  getGuests(): Observable<any[]> {
    return this.http.get<any[]>(this.baseUrl);
  }
}
