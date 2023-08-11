package com.app.shipment.exceptions;

public class ProductNotAvailable extends RuntimeException {
    public ProductNotAvailable(String message) {
        super(message);
    }
}
