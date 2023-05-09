package com.app.shipment.advice;

import com.app.shipment.exceptions.Duplicate;
import com.app.shipment.exceptions.EmptyList;
import com.app.shipment.exceptions.ProductNotFound;
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

    @ExceptionHandler(EmptyList.class)
    public ResponseEntity<String> handleEmptyListException(EmptyList el) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(el.getLocalizedMessage());
    }

    @ExceptionHandler(Duplicate.class)
    public ResponseEntity<String> handleDuplicateException(Duplicate d) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Duplication alert. " + d.getLocalizedMessage());
    }
}
