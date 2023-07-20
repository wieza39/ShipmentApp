package com.app.shipment.customers.service;

import com.app.shipment.customers.exceptions.CustomerNotFound;
import com.app.shipment.customers.models.Address;
import com.app.shipment.customers.models.AddressType;
import com.app.shipment.customers.models.Customer;
import com.app.shipment.customers.models.dto.CustomerDeliveryResponse;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerServiceClient {

    private final CustomerService customerService;

    public CustomerServiceClient(CustomerService customerService) {
        this.customerService = customerService;
    }

    public CustomerDeliveryResponse getCustomerDeliveryDetails(String login) {
        CustomerDeliveryResponse customerDetails = new CustomerDeliveryResponse();
        Optional<Customer> customer = customerService.getCustomerByLogin(login);
        Optional<Address> address = customerService.getCustomerAddressByType(AddressType.DELIVERY, login);

        if(customer.isPresent() && address.isPresent()) {
            customerDetails.setName(customer.get().getName());
            customerDetails.setSurname(customer.get().getSurname());
            customerDetails.setPhone(customer.get().getPhone());
            customerDetails.setEmail(customer.get().getEmail());
            customerDetails.setAddress(address.get());
        } else {
            throw new CustomerNotFound("Verify your login");
        }
        return customerDetails;
    }
}
