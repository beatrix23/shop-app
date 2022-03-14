import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {HttpClientModule} from '@angular/common/http';
import {FormsModule} from '@angular/forms';

import {AppComponent} from './app.component';
import {AppRoutingModule} from "./app-routing.module";
import {CustomerDetailComponent} from "./customers/customer-detail/customer-detail.component";
import {CustomerNewComponent} from "./customers/customer-new/customer-new.component";
import {CustomersComponent} from "./customers/customers.component";
import {CustomerListComponent} from "./customers/customer-list/customer-list.component";
import {CustomerService} from "./customers/shared/customer.service";
import {SnakeComponent} from "./snake/snake.component";

@NgModule({
  declarations: [
    AppComponent,
    CustomerDetailComponent,
    CustomerListComponent,
    CustomersComponent,
    CustomerNewComponent,
    SnakeComponent,
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    AppRoutingModule,
  ],
  providers: [CustomerService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
