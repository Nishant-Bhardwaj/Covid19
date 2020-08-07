import { Injectable } from '@angular/core';
import { HttpClientModule, HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class HomepageService {

  constructor(
  private http: HttpClient
  ) { }

  getDataService(){
    return this.http.get('http://localhost:8097/district');
  }
}
