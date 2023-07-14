package com.app.shipment.customers.controller;

import com.app.shipment.customers.models.Customer;
import com.app.shipment.customers.models.dto.CustomerDeliveryResponse;
import com.app.shipment.customers.service.CustomerService;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Customer>> getALlCustomers() {
        List<Customer> customerList = customerService.getAllCustomers();
        return ResponseEntity.ok(customerList);
    }

    @GetMapping("/customerDetails")
    public ResponseEntity<Optional<Customer>> getCustomer(@RequestParam String login) {
        Optional<Customer> customer = customerService.getCustomerByLogin(login);
        return ResponseEntity.ok(customer);
    }

    @GetMapping("/deliveryDetails")
    public ResponseEntity<CustomerDeliveryResponse> getCustomerDeliveryDetails(@RequestParam String login) {
        CustomerDeliveryResponse customerDetails = customerService.getCustomerDeliveryDetails(login);
        return ResponseEntity.ok(customerDetails);
    }
}
