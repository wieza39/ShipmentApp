package com.app.shipment.ordermanagment.config;

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
}
