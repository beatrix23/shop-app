import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {CustomerDetailComponent} from "./customers/customer-detail/customer-detail.component";
import {CustomersComponent} from "./customers/customers.component";
import {CustomerNewComponent} from "./customers/customer-new/customer-new.component";
import {CustomerListComponent} from "./customers/customer-list/customer-list.component";
import {SnakeComponent} from "./snake/snake.component";

const routes: Routes = [
  // { path: '', redirectTo: '/home', pathMatch: 'full' },
  {path: 'customers', component: CustomersComponent},
  {path: 'customer/detail/:id', component: CustomerDetailComponent},
  {path: 'customers/new', component: CustomerNewComponent},
  {path: 'customers/list', component: CustomerListComponent},
  {path: 'snake', component: SnakeComponent},


];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
