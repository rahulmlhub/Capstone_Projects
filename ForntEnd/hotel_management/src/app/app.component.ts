import { Component, OnInit } from '@angular/core';
import { Route, Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent  implements OnInit{

  title = 'hotel_management';

  isLoggedIn: boolean = false;
  tokens: string | null = localStorage.getItem('jwtToken');
  
  constructor(private route: Router)
  {}

  ngOnInit(): void {
    console.log(this.tokens)
    if (this.tokens) {
      this.isLoggedIn = true;
    }
   
  }
 
  
  
}
