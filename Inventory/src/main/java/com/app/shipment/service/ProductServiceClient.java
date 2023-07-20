package com.app.shipment.service;

import com.app.shipment.exceptions.Duplicate;
import com.app.shipment.exceptions.EmptyList;
import com.app.shipment.exceptions.ProductNotFound;
import com.app.shipment.exceptions.WarehouseNotFound;
import com.app.shipment.model.Product;
import com.app.shipment.model.dto.ProductResponse;
import com.app.shipment.warehouse.model.Warehouse;
import com.app.shipment.repository.ProductRepository;
import com.app.shipment.warehouse.service.WarehouseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceClient {

    private final ProductService productService;

    public ProductServiceClient(ProductService productService) {
        this.productService = productService;
    }


    public ProductResponse withdrawProductBySku(String sku, int quantity) {

        ProductResponse productResponse = new ProductResponse();
        Optional<Product> product = productService.getProductBySku(sku);

        if(product.isPresent()) {
            productService.updateQuantityBySku(product, quantity);

            productResponse.setSku(product.get().getSku());
            productResponse.setQuantity(quantity);
            productResponse.setPrice(product.get().getPrice() * quantity);
            productResponse.setWeight(product.get().getWeight() * quantity);

            return productResponse;
        } else {
            throw new ProductNotFound("Product with SKU " + sku + " not found.");
        }
    }

    /** This getProductBySku() method simply returns productResponse info to OrderManagement Service.
     * Method create basically just for testing connection between services and passed answer.*/
    public ProductResponse getProductBySku(String sku) {

            ProductResponse productResponse = new ProductResponse();
            Optional<Product> product = productService.getProductBySku(sku);

            if(product.isPresent()) {
                productResponse.setSku(product.get().getSku());
                productResponse.setQuantity(product.get().getQuantity());
                productResponse.setPrice(product.get().getPrice());
                productResponse.setWeight(product.get().getWeight());

                return productResponse;
            } else {
                throw new ProductNotFound("Product with SKU " + sku + " not found.");
            }
    }
}
