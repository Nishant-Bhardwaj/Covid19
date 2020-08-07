import { Component, OnInit } from '@angular/core';
import { HomepageService} from './../homepage/homepage.service';

@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  styleUrls: ['./homepage.component.css']
})
export class HomepageComponent implements OnInit {

  record

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
    });
  }

}
