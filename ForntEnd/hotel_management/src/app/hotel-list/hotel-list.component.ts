// hotel-list.component.ts
import { Component, OnInit } from '@angular/core';
import { HotelListService } from '../hotel-list.service';

@Component({
  selector: 'app-hotel-list',
  templateUrl: './hotel-list.component.html',
  styleUrls: ['./hotel-list.component.css']
})
export class HotelListComponent implements OnInit {
  hotels: any[] | undefined;

  constructor(private hotelService: HotelListService) {}

  ngOnInit(): void {
    this.getHotels();
  }

  getHotels(): void {
    this.hotelService.getHotels()
      .subscribe((hotels: any[] | undefined) => this.hotels = hotels);
  }
}
