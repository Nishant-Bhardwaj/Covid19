import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class VaccineService {

  constructor(
    private http: HttpClient
  ) { }

  callVaccineBooking(){
    console.log("Service to backend")
    return this.http.get("http://localhost:9999/order/3/meerut/");
  }
}
