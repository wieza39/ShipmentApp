package com.app.shipment.customers.service;


import com.app.shipment.customers.exceptions.AddressAlreadyExistException;
import com.app.shipment.customers.models.Address;
import com.app.shipment.customers.models.AddressType;
import com.app.shipment.customers.models.Customer;

import com.app.shipment.customers.repository.AddressRepository;
import com.app.shipment.customers.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final AddressRepository addressRepository;

    public CustomerService(CustomerRepository customerRepository, AddressRepository addressRepository) {
        this.customerRepository = customerRepository;
        this.addressRepository = addressRepository;
    }

    public Address addNewAddress(Address address, String login) {
        Optional<List<Address>> addressListOpt = getAddressByCustomerLogin(login);

        if(addressListOpt.isPresent()) {
            List<Address> addressList = addressListOpt.get();
            boolean addressExist = addressList.stream().anyMatch(a ->
                    a.getStreet().equals(address.getStreet()) &&
                            a.getBuilding().equals(address.getBuilding()) &&
                            a.getFlat().equals(address.getFlat()) &&
                            a.getPostalCode().equals(address.getPostalCode()) &&
                            a.getCity().equals(address.getCity()) &&
                            a.getCountry().equals(address.getCountry())
            );
            if(addressExist) {
                throw new AddressAlreadyExistException("Address already exist.");
            }
        }
        address.setType(AddressType.OPTIONAL);
        address.setCustomer(getCustomerByLogin(login).get());
        addressRepository.save(address);

        return address;
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

    public Optional<List<Address>> getAddressByCustomerLogin(String login) {
        return addressRepository.findByCustomerLogin(login);
    }


}

