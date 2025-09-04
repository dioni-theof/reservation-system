package com.dtheof.reservation_system.Mappers;

import com.dtheof.reservation_system.dto.CustomersDtos.CustomerDto;
import com.dtheof.reservation_system.dto.CustomersDtos.CustomerInfoDto;
import com.dtheof.reservation_system.entities.Customer;

public class CustomerMapper {
    public static CustomerDto mappingcustomerToCustomerDto(Customer customer){
        return new CustomerDto(customer.getName(),
                customer.getEmail(),
                customer.getPhone(),
                customer.getCreatedOn(),
                customer.getLastUpdatedOn(),
                customer.getReservations());
    }
    public static CustomerInfoDto mappingcustomerToCustomerInfoDto(Customer customer){
        return new CustomerInfoDto(customer.getName(),
                customer.getEmail(),
                customer.getPhone(),
                customer.getCreatedOn(),
                customer.getLastUpdatedOn());
    }
}
