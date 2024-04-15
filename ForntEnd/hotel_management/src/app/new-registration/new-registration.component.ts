import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { RegistrationService } from '../registration.service';

@Component({
  selector: 'app-new-registration',
  templateUrl: './new-registration.component.html',
  styleUrls: ['./new-registration.component.css']
})
export class NewRegistrationComponent {

  registerForm: FormGroup;
  errorMessage: string | undefined;

  constructor(private fb: FormBuilder, private registrationService: RegistrationService) {
    this.registerForm = this.fb.group({
      userName: ['', [Validators.required]],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(6)]],
      role: ['', [Validators.required]] 
    });
  }

  onSubmit(): void {
    if (this.registerForm.valid) {
      this.registrationService.registerUser(this.registerForm.value).subscribe(
        response => {
          console.log(response);
        },
        error => {
          console.error(error);
          this.errorMessage = error;
        }
      );
    }
  }
  
}
