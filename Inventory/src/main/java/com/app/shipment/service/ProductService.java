package com.app.shipment.service;

import com.app.shipment.exceptions.Duplicate;
import com.app.shipment.exceptions.EmptyList;
import com.app.shipment.exceptions.ProductNotFound;
import com.app.shipment.exceptions.WarehouseNotFound;
import com.app.shipment.model.Product;
import com.app.shipment.model.dto.ProductDTO;
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
public class ProductService {

    private ProductRepository productRepository;
    private WarehouseService warehouseService;

    public ProductService(ProductRepository productRepository, WarehouseService warehouseService) {
        this.productRepository = productRepository;
        this.warehouseService = warehouseService;
    }

    //GET

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(Long id) {
        return Optional.ofNullable(productRepository.findById(id).orElseThrow(() -> new ProductNotFound("Product with id:" + id + " doesn't exist.")));
    }

    public ProductResponse getProductBySku(String sku) {

            ProductResponse productResponse = new ProductResponse();
            Optional<Product> product = productRepository.findProductBySku(sku);

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
    public Product addNewProduct(Product product, String location) {
        Optional<Warehouse> warehouse = warehouseService.getWarehouseByLocation(location);
        if(warehouse.isEmpty()) {
            throw new WarehouseNotFound("Warehouse in " + location + " doesn't exist. Choose different one.");
        }
        if(productRepository.existsBySku(product.getSku())) {
            throw new Duplicate("This SKU:" + product.getSku() + " already exist in database");
        }
        product.setWarehouse(warehouse.get());
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
