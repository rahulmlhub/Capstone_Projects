import { Component, Inject, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HotelListService } from '../hotel-list.service';
import * as $ from 'jquery';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';


@Component({
  selector: 'app-hotel-popup',
  templateUrl: './hotel-popup.component.html',
  styleUrls: ['./hotel-popup.component.css']
})
export class HotelPopupComponent implements OnInit {
  hotelId: string;
  hotel: any;

  constructor(private route: ActivatedRoute, private hotelService: HotelListService, @Inject(MAT_DIALOG_DATA) public data: any) {
    this.hotelId = data.hotelId; // Access hotelId from the passed data
  }

  ngOnInit(): void {
    this.loadHotelDetails(this.hotelId);
  }

  loadHotelDetails(hotelId: string) {
    this.hotelService.getHotelById(hotelId).subscribe(
      (data: any) => {
        this.hotel = data;
      },
      (error: any) => {
        console.error('Error fetching hotel details:', error);
        // Handle error here
      }
    );
  }
}
