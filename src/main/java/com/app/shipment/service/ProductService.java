package com.app.shipment.service;

import com.app.shipment.exceptions.Duplicate;
import com.app.shipment.exceptions.EmptyList;
import com.app.shipment.exceptions.ProductNotFound;
import com.app.shipment.model.Product;
import com.app.shipment.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    //GET

    public List<Product> getAllProducts() {
        List<Product> productList = productRepository.findAll();
        return productList;
    }

    public Optional<Product> getProductById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        if (!product.isPresent()) {
            throw new ProductNotFound("Product with id:" + id + " doesn't exist.");
        }
        return product;
    }

    public Optional<Product> getProductBySku(String sku) {
        Optional<Product> product = productRepository.findProductBySku(sku);
        if (!product.isPresent()) {
            throw new ProductNotFound("Product with SKU:" + sku + " doesn't exist.");
        }
        return product;
    }

    public List<Product> getAvailableProducts() {
        List<Product> available = productRepository.findAll()
                .stream()
                .filter(p -> p.getQuantity() > 0)
                .collect(Collectors.toList());
        if(available.isEmpty()) {
            throw new EmptyList("No available products found.");
        }
        return available;
    }

    public List<Product> getUnavailableProducts() {
        List<Product> unavailable = productRepository.findAll()
                .stream()
                .filter(p -> p.getQuantity() == 0)
                .collect(Collectors.toList());
        if(unavailable.isEmpty()) {
            throw new EmptyList("No unavailable products found");
        }
        return unavailable;
    }

    public void getProductsFromWarehouse(String location) {}

    //POST
    @Transactional
    public Product addNewProduct(Product product) {
        if(productRepository.existsBySku(product.getSku())) {
            throw new Duplicate("This SKU:" + product.getSku() + " already exist in database");
        }
        productRepository.save(product);
        return product;
    }

    public void importProducts(List<Product> newProductList) {}

    //PUT
        //update by SKU
    public void updateNameBySku(Long id, String name) {}

    public void updateQuantityBySku() {}

    public void updateWeightBySku() {}

    public void updatePriceBySku() {}

    public void changeWarehouseBySku() {}


    //DELETE
    public void deleteProductBySKU(String sku) {

    }


}
