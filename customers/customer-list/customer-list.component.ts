import {Component, OnInit} from '@angular/core';
import {Customer} from "../shared/customer.model";
import {CustomerService} from "../shared/customer.service";
import {Router} from "@angular/router";
import {Dtos} from "../shared/dtos";


@Component({
  moduleId: module.id,
  selector: 'ubb-customer-list',
  templateUrl: './customer-list.component.html',
  styleUrls: ['./customer-list.component.css'],
})

export class CustomerListComponent implements OnInit {
  errorMessage: string;
  customers: Array<Customer>
  selectedCustomer: Customer;

  constructor(private customerService: CustomerService,
              private router: Router) {
  }

  ngOnInit(): void {
    this.getCustomers();
  }

  getCustomers() {
    this.customerService.getCustomers()
      .subscribe(
        customers => this.customers = Array.from(customers.dtos),
        error => this.errorMessage = <any>error
      );
  }

  onSelect(customer: Customer): void {
    this.selectedCustomer = customer;
  }

  gotoDetail(): void {
    this.router.navigate(['/customer/detail', this.selectedCustomer.id]);
  }
}
