import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable, catchError, tap, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RegistrationService {

  private baseUrl = 'http://localhost:8080/auth'; // Replace with your API URL

  constructor(private http: HttpClient, private router: Router) {}

  registerUser(userCredentialDto: any): Observable<any> {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.http.post<any>(`${this.baseUrl}/register`, userCredentialDto, { headers: headers })
      .pipe(
        tap((response) => {
          console.log('Response:', response); // Log the response
          this.router.navigate(['/login']); // Navigate to login page after successful registration
        }),
        catchError(this.handleError)
      );
  }
  

  private handleError(error: HttpErrorResponse): Observable<never> {
    let errorMessage = 'Unknown error occurred';
    if (error.error instanceof ErrorEvent) {
      // Client-side error
      errorMessage = `Error: ${error.error.message}`;
    } else {
      // Server-side error
      errorMessage = `Error Code: ${error.status}\nMessage: ${error.message}`;
    }
    // console.error(errorMessage);
    return throwError(errorMessage);
  }
}
