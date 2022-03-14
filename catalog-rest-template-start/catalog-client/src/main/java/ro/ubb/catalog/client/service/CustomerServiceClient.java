package ro.ubb.catalog.client.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ro.ubb.catalog.web.dto.CustomerDto;
import ro.ubb.catalog.web.dto.Dtos;


import java.util.Set;

@Component
public class CustomerServiceClient {

    private static final String URL = "http://localhost:8080/api/customers";
    private static final Logger log = LoggerFactory.getLogger(CustomerServiceClient.class);

    @Autowired
    private RestTemplate restTemplate;

    public Set<CustomerDto> getAllCustomers() {
        log.trace("getAllCustomers --- method started");
        Dtos<CustomerDto> customersDto = restTemplate.exchange(URL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Dtos<CustomerDto>>() {}).getBody();
        log.trace("customers dtos ={}", customersDto);
        return customersDto.getDtos();
    }

    public CustomerDto addCustomer(CustomerDto newCustomer) {
        log.trace("addCustomer --- method started");
        CustomerDto customerDto = restTemplate.postForObject(URL, newCustomer, CustomerDto.class);
        log.trace("customerDto ={}", customerDto);
        return customerDto;
    }

    public void updateCustomer(Integer id, CustomerDto updated) {
        log.trace("updateCustomer --- method started");
        log.trace("customer dto={}", updated);
        log.trace("customer id={}", id);
        restTemplate.put(URL + "/{id}", updated, id);
    }

    public void deleteCustomer(Integer id) {
        restTemplate.delete(URL + "/{id}", id);
    }

    public CustomerDto findOneCustomer(Integer id) {
        log.trace("customer's id={}", id);
        CustomerDto customerDto = restTemplate.getForObject(URL + "/{id}", CustomerDto.class, id);
        log.trace("customerDto={}", customerDto);
        return customerDto;
    }
}
