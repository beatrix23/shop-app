package ro.ubb.catalog.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.ubb.catalog.core.model.Customer;
import ro.ubb.catalog.core.service.GenericService;
import ro.ubb.catalog.web.converter.CustomerConverter;
import ro.ubb.catalog.web.dto.CustomerDto;
import ro.ubb.catalog.web.dto.Dtos;

import java.util.List;
import java.util.Set;

@RestController
public class CustomerController {
    private static final Logger log = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    private GenericService<Customer> customerService;

    @Autowired
    private CustomerConverter customerConverter;

    @RequestMapping(value = "/customers")
    Dtos<CustomerDto> getAllCustomers() {
        log.trace("getAllCustomers --- method started");
        List<Customer> customers = customerService.getAllEntities();
        log.trace("customers from db ={}", customers);
        Set<CustomerDto> customerDtos = customerConverter.convertModelsToDtos(customers);
        log.trace("customers dtos ={}", customerDtos);
        return new Dtos<>(customerDtos);
    }

    @RequestMapping(value = "/customers", method = RequestMethod.POST)
    CustomerDto addCustomer(@RequestBody CustomerDto customerDto) {
        log.trace("customer to add={}", customerDto);
        Customer customerToAdd = customerConverter.convertDtoToModel(customerDto);
        log.trace("customer to add={}", customerToAdd);
        Customer added = customerService.addEntity(customerToAdd);
        CustomerDto converted = customerConverter.convertModelToDto(added);
        log.trace("customer dto after add={}", converted);
        return converted;
    }

    @RequestMapping(value = "customers/{id}", method = RequestMethod.PUT)
    CustomerDto updateCustomer(@PathVariable Integer id, @RequestBody CustomerDto customerDto) {
        log.trace("customer to update={}", customerDto);
        log.trace("customer id={}", id);
        Customer customerToUpdate = customerConverter.convertDtoToModel(customerDto);
        log.trace("customer model={}", customerToUpdate);
        Customer updated = customerService.updateEntity(id, customerToUpdate);
        log.trace("customer updated={}", updated);
        CustomerDto toReturn = customerConverter.convertModelToDto(updated);
        log.trace("customer dto tu return={}", toReturn);
        return toReturn;
    }

    @RequestMapping(value = "customers/{id}", method = RequestMethod.DELETE)
    ResponseEntity<?> deleteCustomer(@PathVariable Integer id) {
        log.trace("customer delete --- method started");
        log.trace("customer's  id={}", id);
        customerService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @RequestMapping(value = "customers/{id}", method = RequestMethod.GET)
    CustomerDto findOneCustomer(@PathVariable Integer id) {
        log.trace("customer's id={}", id);
        Customer customer = customerService.findOneEntity(id);
        log.trace("customer found={}", customer);
        CustomerDto customerDto = customerConverter.convertModelToDto(customer);
        log.trace("customerDto = {}", customerDto);
        return customerDto;
    }
}
