import { Component, OnInit } from '@angular/core';
import {VaccineService} from './vaccine.service';

@Component({
  selector: 'app-vaccine',
  templateUrl: './vaccine.component.html',
  styleUrls: ['./vaccine.component.css']
})
export class VaccineComponent implements OnInit {

  result

  constructor(
    private vaccineService : VaccineService
  ) {}

  ngOnInit(): void {
    this.callVaccineApp();
  }

  callVaccineApp() {
    console.log("Called Vaccince App")
    this.vaccineService.callVaccineBooking().subscribe(data=>{
      console.log("data "+data);
      this.result = data;
    });

    console.log("Result: " + this.result);

  }



}
