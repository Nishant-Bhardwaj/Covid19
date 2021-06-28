import { HttpClient } from '@angular/common/http';
import { analyzeAndValidateNgModules } from '@angular/compiler';
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
    return this.http.get<String>("http://localhost:9999/order/10/meerut/", {responseType:'text' as 'json'});
  }
  
}
