package com.app.shipment.customers.advice;

import com.app.shipment.customers.exceptions.CustomerNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomerControllerAdvice {

    @ExceptionHandler(CustomerNotFound.class)
    public ResponseEntity<String> customerNotFoundHandler(CustomerNotFound cnf) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer not found. " + cnf.getLocalizedMessage());
    }
}
