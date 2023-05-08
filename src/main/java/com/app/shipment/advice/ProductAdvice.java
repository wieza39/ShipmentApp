package com.app.shipment.advice;

import com.app.shipment.exceptions.ProductNotFound;
import com.app.shipment.model.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ProductAdvice {

    @ExceptionHandler(ProductNotFound.class)
    public ResponseEntity<String> handleProductNotFoundException(ProductNotFound pnf) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Didn't find your product. " + pnf.getLocalizedMessage());
    }
}
