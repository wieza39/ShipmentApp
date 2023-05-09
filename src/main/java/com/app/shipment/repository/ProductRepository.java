package com.app.shipment.repository;

import com.app.shipment.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findProductBySku(String sku);
    boolean existsBySku(String sku);
}
