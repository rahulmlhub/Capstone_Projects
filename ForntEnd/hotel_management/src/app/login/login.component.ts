import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  username: string | undefined;
  password: string | undefined;
  errorMessage: string | undefined; // Variable to hold error message

  constructor(private http: HttpClient, private router: Router) {}

  login() {
    // Define the request body
    const body = {
      email: this.username,
      password: this.password
    };
  
    // Define the headers
    const headers = new HttpHeaders({
      'Content-Type': 'application/json'
    });
  
    // Make the POST request to the backend
    this.http.post<any>('http://localhost:8080/api/users/login', body)
      .subscribe(
        (result: any) => {
          console.log('Login successful:', result);
          if (result.jwtToken) {
            localStorage.setItem("jwtToken", result.jwtToken);
          }
          // Redirect to another page or perform other actions after successful login
           this.router.navigate(['/home']);
        },
        error => {
          console.error('Login failed:', error);
          if (error.error instanceof ErrorEvent) {
            // Client-side error
            console.error('An error occurred:', error.error.message);
          } else {
            // Backend error
            console.error(`Backend returned code ${error.status}, body was: `, error.error);
            // Set error message to display on the login page
            this.errorMessage = 'Invalid username or password. Please try again.';
          }
          // Handle the error, show an error message, etc.
        }
      );
  }
  
}
