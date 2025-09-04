package com.dtheof.reservation_system.controller;


import com.dtheof.reservation_system.dto.CustomersDtos.AllCustomersDto;
import com.dtheof.reservation_system.dto.CustomersDtos.CustomerInfoDto;
import com.dtheof.reservation_system.dto.SuccessDtos.SuccessResponse;
import com.dtheof.reservation_system.dto.CustomersDtos.CustomerDto;
import com.dtheof.reservation_system.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping(path = "/api/v1/customer")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping(value = "/create-customer", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Void> createCustomer(@Valid @RequestBody CustomerDto customerDto) {
        String addedCustomer = customerService.createCustomer(customerDto);

        return ResponseEntity.created(URI.create("/api/v1/customer/" + addedCustomer + "/get-info")).build();
    }

    @GetMapping(value = "/all-customers")
    public ResponseEntity<AllCustomersDto> getAllCustomers(@RequestParam(name = "size", defaultValue = "5") int size, @RequestParam(name = "page", defaultValue = "0") int page) {
        AllCustomersDto results = customerService.findCustomers(PageRequest.of(page, size));

        return ResponseEntity.ok(results);
    }
    @GetMapping(value = "/{email}/get-info",consumes = "application/json", produces = "application/json")
    public ResponseEntity<CustomerInfoDto> getInfoCustomerByEmail(@PathVariable String email){
        CustomerInfoDto customer =customerService.findCustomerByEmail(email);

        return ResponseEntity.ok(customer);
    }


    @DeleteMapping(value = "/{email}/delete-customer", consumes = "application/json", produces = "application/json")
    public ResponseEntity<SuccessResponse> deleteCustomer(@PathVariable String email) {
        SuccessResponse deletedCustomer = customerService.deletedCustomer(email);

        return ResponseEntity.ok(deletedCustomer);
    }


    @PutMapping(value = "/{email}/update-customer",consumes = "application/json", produces = "application/json")
    public ResponseEntity<CustomerDto> updateCustomer(@PathVariable String email, @RequestBody CustomerDto customerDto){
        CustomerDto updateCustomerDto = customerService.updateCustomer(email, customerDto);

        return ResponseEntity.ok(updateCustomerDto);
    }
  }
