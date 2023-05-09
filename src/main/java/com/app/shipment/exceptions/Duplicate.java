package com.app.shipment.exceptions;

public class Duplicate extends RuntimeException{
    public Duplicate(String message) {
        super(message);
    }
}
