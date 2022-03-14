import {Injectable} from '@angular/core';

import {HttpClient} from "@angular/common/http";

import {Observable} from "rxjs";
import {map} from "rxjs/operators";
import {Customer} from "./customer.model";
import {Dtos} from "./dtos";

@Injectable()
export class CustomerService {
  private customersURL = 'http://localhost:8080/api/customers';

  constructor(private httpClient: HttpClient) {

  }
   getCustomers(): Observable<Dtos<Customer>> {
     return this.httpClient.get<Dtos<Customer>>(this.customersURL);
   }

   getCustomer(id: number): Observable<Customer> {
    return this.getCustomers()
      .pipe(map(customers => customers.dtos.find(customer => customer.id === id)));
   }

   update(customer): Observable<Customer> {
    const url = `${this.customersURL}/${customer.id}`;
    return this.httpClient
      .put<Customer>(url, customer);
   }

  saveCustomer(firstName, lastName, address, phone, email): Observable<Customer> {
    const customer = {firstName, lastName, address, phone, email};
    return this.httpClient
      .post<Customer>(this.customersURL, customer);
  }
}
