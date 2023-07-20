package com.app.shipment.customers.service;

import com.app.shipment.customers.exceptions.CustomerNotFound;
import com.app.shipment.customers.models.Address;
import com.app.shipment.customers.models.AddressType;
import com.app.shipment.customers.models.Customer;
import com.app.shipment.customers.models.dto.CustomerDeliveryResponse;
import com.app.shipment.customers.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final AddressService addressService;

    public CustomerService(CustomerRepository customerRepository, AddressService addressService) {
        this.customerRepository = customerRepository;
        this.addressService = addressService;
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Optional<Customer> getCustomerByLogin(String login) {
        List<Customer> customerList = getAllCustomers();
        return customerList.stream().filter(c -> Objects.equals(c.getLogin(), login)).findFirst();
    }

    public Optional<Address> getCustomerAddressByType(AddressType addressType, String login) {
        Optional<Customer> customer = getCustomerByLogin(login);
        return customer.flatMap(c -> c.getAddressList()
                .stream()
                .filter(a -> a.getType() == addressType)
                .findFirst());
    }


}

