package com.app.shipment.service;

import com.app.shipment.exceptions.ProductNotFound;
import com.app.shipment.model.Product;
import com.app.shipment.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        if(id == null) {
            throw new ProductNotFound("Product with id:" + id + " doesn't exist.");
        }
        return productRepository.findById(id);
    }

    public void getAvailableProducts() {}

    public void getUnavailableProducts() {}

    public void getProductsFromWarehouse(String location) {}

    //POST

    public Product addNewProduct(Product product) {
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
