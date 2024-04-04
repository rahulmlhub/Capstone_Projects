import { Component } from '@angular/core';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  username: string | undefined;
  password: string | undefined;

  constructor() {}

  login() {
    // Implement your login logic here
    console.log('Logging in with username:', this.username, 'and password:', this.password);
    // Example: You might want to send a request to a backend API to authenticate the user
  }
}
