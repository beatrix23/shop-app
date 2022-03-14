package ro.ubb.catalog.core.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.ubb.catalog.core.model.Customer;
import ro.ubb.catalog.core.repository.CustomerRepository;

import java.util.List;


@Service
public class CustomerService implements GenericService<Customer> {

    private static final Logger log = LoggerFactory.getLogger(CustomerService.class);

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public List<Customer> getAllEntities() {
        log.trace("getAllCustomers --- method started");

        List<Customer> customers = customerRepository.findAll();

        log.trace("getAllCustomers: customers={}", customers);

        return customers;
    }

    @Override
    public Customer findOneEntity(Integer id) {
        log.trace("getOneCustomer --- method started");
        log.trace("customer's id={}", id);
        Customer customer = customerRepository.findById(id).orElseThrow();
        log.trace("customer found={}", customer);
        return customer;
    }

    @Override
    public Customer addEntity(Customer entity) {
        log.trace("addCustomer --- method started");
        Customer customer = customerRepository.save(entity);
        log.trace("customer to add={}", customer);
        return customer;
    }

    @Transactional
    @Override
    public Customer updateEntity(Integer id, Customer entity) {
        log.trace("updateCustomer --- method started");
        Customer customerToUpdate = customerRepository.findById(id).orElseThrow();
        log.trace("customer before update={}", customerToUpdate);
        customerToUpdate.setFirstName(entity.getFirstName());
        customerToUpdate.setLastName(entity.getLastName());
        customerToUpdate.setAddress(entity.getAddress());
        customerToUpdate.setPhone(entity.getPhone());
        customerToUpdate.setEmail(entity.getEmail());
        log.trace("product updated={}", customerToUpdate);
        return customerToUpdate;
    }

    @Override
    public void deleteById(Integer id) {
        log.trace("delete customer --- method started");
        log.trace("customer's id={}", id);
        customerRepository.deleteById(id);
    }
}
