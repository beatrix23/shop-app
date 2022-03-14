import {Component, OnInit} from '@angular/core';
import {CustomerService} from "../shared/customer.service";

@Component({
  selector: 'ubb-customer-new',
  templateUrl: './customer-new.component.html',
  styleUrls: ['./customer-new.component.css']
})
export class CustomerNewComponent implements OnInit {

  constructor(private customerService: CustomerService) {
  }

  ngOnInit(): void {
  }

  saveCustomer(firstName, lastName, address, phone, email) {
    console.log("CONSOLE HELLO", firstName, lastName, address, phone, email);
    this.customerService.saveCustomer(firstName, lastName, address, phone, email)
      .subscribe(customer => console.log(customer));
  }
}
