import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomepageComponent } from './homepage/homepage.component';
import { VaccineComponent } from './vaccine/vaccine.component';

const routes: Routes = [
  { path: 'indiaReport' , component: HomepageComponent },
  { path: 'vaccine' , component: VaccineComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
