import { Component, OnInit } from '@angular/core';
import { GuestService } from '../guest.service';

@Component({
  selector: 'app-guest',
  templateUrl: './guest.component.html',
  styleUrls: ['./guest.component.css']
})
export class GuestComponent implements OnInit {
  guests: any[] = []; // Initialize as an empty array

  constructor(private guestService: GuestService) {}

  ngOnInit(): void {
    this.getGuest();
  }

  getGuest(): void {
    this.guestService.getGuests()
      .subscribe(guests => {
        this.guests = guests; // Assign the response directly
      });
  }
}