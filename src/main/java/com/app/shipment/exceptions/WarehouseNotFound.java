package com.app.shipment.exceptions;

public class WarehouseNotFound extends RuntimeException {
    public WarehouseNotFound(String message) {
        super(message);
    }
}
