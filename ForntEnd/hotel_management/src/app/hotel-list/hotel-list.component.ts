// hotel-list.component.ts
import { Component, OnInit } from '@angular/core';
import { HotelListService } from '../hotel-list.service';
import { FormBuilder, FormGroup } from '@angular/forms';
import { HotelPopupComponent } from '../hotel-popup/hotel-popup.component';
import { MatDialog } from '@angular/material/dialog';

@Component({
  selector: 'app-hotel-list',
  templateUrl: './hotel-list.component.html',
  styleUrls: ['./hotel-list.component.css']
})
export class HotelListComponent implements OnInit {
  hotels: any[] | undefined;
  filteredHotels: any[] | undefined; // Initialize as undefined
  searchForm:FormGroup;
  searchText: string = ''; // Initialize searchText as an empty string
  
  
  constructor(private hotelService: HotelListService,private formBuilder:FormBuilder,public dialog: MatDialog) {
    this.searchForm=this.formBuilder.group({
      searchKey:[''],
    });
  }

  ngOnInit(): void {
    this.getHotels();
  }

  getHotels(): void {
    this.hotelService.getHotels()
      .subscribe((hotels: any[] | undefined) => {
        this.hotels = hotels;
        this.filteredHotels = hotels; // Initially, display all hotels
      });
  }

  filterHotels(): void {
    const key=this.searchForm.get('searchKey')?.value;
    console.log('Testing',key);
    this.filteredHotels=this.hotels?.filter(hotel=>hotel.name.toLowerCase().includes(key.toLowerCase()))
  }
  toggleFacilities(hotel: any) {
    hotel.showFacilities = !hotel.showFacilities;
  }

  openPopup(hotelId: string) {
    this.dialog.open(HotelPopupComponent, {
      width: '400px',
      height: '300px', // adjust width as needed
      data: { hotelId: hotelId } // pass data to the popup if needed
    });
  }
  }

