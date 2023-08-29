package com.app.shipment.ordermanagment.config;

import com.app.shipment.ordermanagment.exceptions.AddressAlreadyExistException;
import com.app.shipment.ordermanagment.exceptions.ProductNotAvailable;
import com.app.shipment.ordermanagment.exceptions.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<String> productNotFoundHandler(ProductNotFoundException pnfe) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found. " + pnfe.getLocalizedMessage());
    }

    @ExceptionHandler(IndexOutOfBoundsException.class)
    public ResponseEntity<String> productNotAvailbableHandler(IndexOutOfBoundsException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Insufficient amount of product. " + exception.getLocalizedMessage());
    }

    @ExceptionHandler(AddressAlreadyExistException.class)
    public ResponseEntity<String> doubledAddressException(AddressAlreadyExistException aae) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Duplication detected. " + aae.getLocalizedMessage());
    }
}
