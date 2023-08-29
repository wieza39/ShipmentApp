package com.app.shipment.ordermanagment.exceptions;

public class ProductNotAvailable extends RuntimeException {
    public ProductNotAvailable(String message) {
        super(message);
    }
}
