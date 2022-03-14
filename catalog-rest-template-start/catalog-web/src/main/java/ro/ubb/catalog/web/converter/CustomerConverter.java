package ro.ubb.catalog.web.converter;

import org.springframework.stereotype.Component;
import ro.ubb.catalog.core.model.Customer;
import ro.ubb.catalog.web.dto.CustomerDto;

@Component
public class CustomerConverter extends BaseConverter<Customer, CustomerDto> {
    @Override
    public Customer convertDtoToModel(CustomerDto dto) {
        Customer customer = Customer.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .address(dto.getAddress())
                .phone(dto.getPhone())
                .email(dto.getEmail())
                .build();
        customer.setId(dto.getId());
        return customer;
    }

    @Override
    public CustomerDto convertModelToDto(Customer customer) {
        CustomerDto customerDto = CustomerDto.builder()
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .address(customer.getAddress())
                .phone(customer.getPhone())
                .email(customer.getEmail())
                .build();
        customerDto.setId(customer.getId());
        return customerDto;
    }
}
