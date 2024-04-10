import { Component } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-hotel',
  templateUrl: './hotel.component.html',
  styleUrls: ['./hotel.component.css']
})
export class HotelComponent {
  availableFacilities: { id: number, name: string }[] = [];
  hotelForm: FormGroup;

  constructor(
    private formBuilder: FormBuilder,
    private http: HttpClient
  ) {
    this.hotelForm = this.formBuilder.group({
      hotelId: [''],
      name: [''],
      address: [''],
      phoneNumber: [''],
      images: [''], // Add images control
      facilities: [[]] // Add facilities control
    });

    // Initialize availableFacilities
    this.availableFacilities = [
      { id: 1, name: 'Swimming Pool' },
      { id: 2, name: 'Gym' },
      { id: 3, name: 'Restaurant' },
      // Add more facilities as needed
    ];
  }

 // Update onSubmit() method in Angular component
 onSubmit() {
  const formData = new FormData();
  formData.append('hotelId', this.hotelForm.get('hotelId')?.value);
  formData.append('name', this.hotelForm.get('name')?.value);
  formData.append('address', this.hotelForm.get('address')?.value);
  formData.append('phoneNumber', this.hotelForm.get('phoneNumber')?.value);
  formData.append('images', this.hotelForm.get('images')?.value);

  // Append selected facilityIds to form data
  const selectedFacilities = this.hotelForm.get('facilities')?.value;
  selectedFacilities.forEach((facilityId: number) => {
    formData.append('facilityId', facilityId.toString());
  });

  // Send POST request to Spring Boot backend
  this.http.post<any>('http://localhost:8081/api/hotels/create', formData).subscribe({
    next: () => {
      alert('Data saved successfully');
      this.hotelForm.reset();
    },
    error: (error) => {
      console.error('Error:', error);
      alert('Failed to save data');
    }
  });
}



  onImageChange(event: any) {
    if (event.target.files.length > 0) {
      const file = event.target.files[0];
      this.hotelForm.get('images')?.setValue(file);
    }
  }
}
