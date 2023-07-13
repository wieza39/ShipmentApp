package com.app.shipment.customers.service;

import com.app.shipment.customers.models.Address;
import com.app.shipment.customers.models.Customer;
import com.app.shipment.customers.repository.AddressRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {

    private final AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public Optional<List<Address>> getAddressByCustomerLogin(String login) {
        return addressRepository.findByCustomerLogin(login);
    }
}
