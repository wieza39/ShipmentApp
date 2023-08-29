package com.app.shipment.customers.exceptions;

public class AddressAlreadyExistException extends RuntimeException{
    public AddressAlreadyExistException(String message) {
        super(message);
    }
}
