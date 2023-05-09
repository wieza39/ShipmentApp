package com.app.shipment.controller;

import com.app.shipment.model.Product;
import com.app.shipment.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Product>> getProductById(@PathVariable Long id) {
        Optional<Product> product = productService.getProductById(id);
        return ResponseEntity.ok(product);
    }

    @GetMapping("/find/available")
    public ResponseEntity<List<Product>> getAllAvailableProducts() {
        List<Product> available = productService.getAvailableProducts();
        return ResponseEntity.ok(available);
    }

    @GetMapping("/find/unavailable")
    public ResponseEntity<List<Product>> getAllUnavailableProducts() {
        List<Product> available = productService.getUnavailableProducts();
        return ResponseEntity.ok(available);
    }

    @PostMapping("/new")
    public ResponseEntity<Product> addNewProduct(@RequestBody Product product) {
        Product newProduct = productService.addNewProduct(product);
        return ResponseEntity.ok(newProduct);
    }
}
