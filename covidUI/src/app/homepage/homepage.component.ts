import { Component, OnInit } from '@angular/core';
import { HomepageService} from './../homepage/homepage.service';

@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  styleUrls: ['./homepage.component.css']
})
export class HomepageComponent implements OnInit {

  record
  state
  totalActive=0
  totalCases=0
  totalRecovered=0
  totalDeceased=0

  constructor(
    private homepageService: HomepageService
  ) { }

  ngOnInit(): void {
    this.getRecord()
  }

  getRecord(){
    this.homepageService.getDataService().subscribe(data=>{
      console.log(data);
      this.record = data;
      this.calculateTotal();
    });
  }

  calculateTotal(){
    console.log("Size: " + this.record.length)
      for(let states=0 ; states <=this.record.length; states++){
        if(this.record[states] != null){
          this.totalCases += this.record[states].confirmedCase
          this.totalActive += this.record[states].activeCase
          this.totalRecovered += this.record[states].recoveredCase
          this.totalDeceased += this.record[states].deceasedCase
        }
      }
  }

}
