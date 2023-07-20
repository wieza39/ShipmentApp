package com.app.shipment.controller;

import com.app.shipment.model.dto.ProductResponse;
import com.app.shipment.service.ProductServiceClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/product")
public class ProductControllerClient {

    private final ProductServiceClient productServiceClient;

    public ProductControllerClient(ProductServiceClient productServiceClient) {
        this.productServiceClient = productServiceClient;
    }

    @GetMapping("/sku")
    public ResponseEntity<ProductResponse> getProductBySku(@RequestParam String sku) {
        ProductResponse product = productServiceClient.getProductBySku(sku);
        return ResponseEntity.ok(product);
    }

    @PatchMapping("/withdraw")
    public ResponseEntity<ProductResponse> withdrawProduct(@RequestParam String sku, int quantity) {
        ProductResponse product = productServiceClient.withdrawProductBySku(sku, quantity);
        return ResponseEntity.ok(product);
    }
}
