package com.app.shipment.customers.controller;

import com.app.shipment.customers.models.Address;
import com.app.shipment.customers.models.dto.CustomerDeliveryResponse;
import com.app.shipment.customers.service.CustomerServiceClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/customers")
public class CustomerControllerClient {

    private final CustomerServiceClient customerServiceClient;

    public CustomerControllerClient(CustomerServiceClient customerServiceClient) {
        this.customerServiceClient = customerServiceClient;
    }

    @GetMapping("/deliveryDetails")
    public ResponseEntity<CustomerDeliveryResponse> getCustomerDeliveryDetails(@RequestParam String login) {
        CustomerDeliveryResponse customerDetails = customerServiceClient.getCustomerDeliveryDetails(login);
        return ResponseEntity.ok(customerDetails);
    }

    @PostMapping("/address/new")
    public ResponseEntity<Void> createAddress(@RequestBody Address address, @RequestParam String login) {
        customerServiceClient.addNewAddress(address,login);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
