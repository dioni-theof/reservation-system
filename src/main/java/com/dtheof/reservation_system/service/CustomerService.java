package com.dtheof.reservation_system.service;

import com.dtheof.reservation_system.Mappers.CustomerMapper;
import com.dtheof.reservation_system.dto.CustomersDtos.AllCustomersDto;
import com.dtheof.reservation_system.dto.CustomersDtos.CustomerDto;
import com.dtheof.reservation_system.dto.CustomersDtos.CustomerInfoDto;
import com.dtheof.reservation_system.dto.SuccessDtos.SuccessResponse;
import com.dtheof.reservation_system.entities.Customer;
import com.dtheof.reservation_system.repo.CustomerRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public String createCustomer(CustomerDto customerDto) {
        Customer newCustomer = new Customer(customerDto.name(),
                customerDto.email(),
                customerDto.phone(),
                customerDto.createdOn(),
                customerDto.lastUpdatedOn());

        return customerRepository.save(newCustomer).getEmail();
    }

    public AllCustomersDto findCustomers(PageRequest pageRequest) {
        Page<Customer> result = customerRepository.findAll(pageRequest);

        List<Customer> customers = result.getContent();
        List<CustomerInfoDto> customersInfoDtos = new ArrayList<>();
        for (Customer customer : customers) {
            CustomerInfoDto customerInfoDto = new CustomerInfoDto(customer.getName(),
                    customer.getEmail(),
                    customer.getPhone(),
                    customer.getCreatedOn(),
                    customer.getLastUpdatedOn());

            customersInfoDtos.add(customerInfoDto);
        }

        return new AllCustomersDto(customersInfoDtos, result.isLast(), result.getTotalPages(), result.getTotalElements());
    }

    public CustomerInfoDto findCustomerByEmail(String email) {
        Customer customer = customerRepository.findCustomerByEmail(email).orElseThrow();

        return CustomerMapper.mappingcustomerToCustomerInfoDto(customer);
    }

    public CustomerDto updateCustomer(String email, CustomerDto customerDto) {
        Customer updateCustomer = customerRepository.findCustomerByEmail(email).orElseThrow();
        if (customerDto.name() != null) {
            updateCustomer.setName(customerDto.name());
        }
        if (customerDto.phone() != null) {
            updateCustomer.setPhone(customerDto.phone());
        }
        updateCustomer.setLastUpdatedOn(Instant.now());
        updateCustomer = customerRepository.save(updateCustomer);

        return CustomerMapper.mappingcustomerToCustomerDto(updateCustomer);
    }

    @Transactional
    public SuccessResponse deletedCustomer(String email) {
        Customer deletedCustomer = customerRepository.findCustomerByEmail(email).orElseThrow();
        customerRepository.deleteCustomerByEmail(email);

        return new SuccessResponse("Success deleted " + deletedCustomer.getEmail());
    }
}


