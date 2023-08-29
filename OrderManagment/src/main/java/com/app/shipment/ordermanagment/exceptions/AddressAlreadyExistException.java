package com.app.shipment.ordermanagment.exceptions;

public class AddressAlreadyExistException extends RuntimeException{
    public AddressAlreadyExistException(String message) {
        super(message);
    }
}
