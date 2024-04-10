// hotel.service.ts
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
// import { HotelDTO } from './hotel.model';

@Injectable({
  providedIn: 'root'
})
export class HotelListService {
  private baseUrl = 'http://localhost:8081/api/hotels/withFacilities'; // Change this to your backend URL

  constructor(private http: HttpClient) {}

  getHotels(): Observable<any[]> {
    return this.http.get<any[]>(this.baseUrl);
  }
}
