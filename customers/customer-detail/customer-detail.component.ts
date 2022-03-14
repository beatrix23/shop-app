import {Component, Input, OnInit} from '@angular/core';
import {CustomerService} from "../shared/customer.service";
import {ActivatedRoute, Params} from "@angular/router";
import {Location} from '@angular/common';
import {Customer} from "../shared/customer.model";
import {switchMap} from "rxjs/operators";


@Component({
  selector: 'ubb-customer-detail',
  templateUrl: './customer-detail.component.html',
  styleUrls: ['./customer-detail.component.css'],
})

export class CustomerDetailComponent implements OnInit {

  @Input() customer: Customer;

  constructor(private customerService: CustomerService,
              private route: ActivatedRoute,
              private location: Location) {

  }

  ngOnInit(): void {
        this.route.params
          .pipe(switchMap((params: Params) => this.customerService.getCustomer(+params['id'])))
            .subscribe(customer => this.customer = customer);
    }

  goBack(): void {
    this.location.back();
  }

  save(): void {
    this.customerService.update(this.customer)
      .subscribe(_ => this.goBack());
  }

}
