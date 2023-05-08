package com.app.shipment.service;

import com.app.shipment.model.Product;
import com.app.shipment.model.Warehouse;
import com.app.shipment.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    ProductRepository productRepository;

    @InjectMocks
    ProductService productService;


    @Test
    void getProductById() {
        //given
        Long id = 1L;
        Warehouse warehouse = new Warehouse();
        Product product = new Product(id, "test name", "A123", 5,4.0f, 50.0f, warehouse);
        Mockito.when(productRepository.findById(id)).thenReturn(Optional.of(product));

        //when
        Optional<Product> optionalProduct = productService.getProductById(id);

        //then
        assertTrue(optionalProduct.isPresent());
        assertEquals(product, optionalProduct.get());

        Mockito.verify(productRepository).findById(id);
    }

    @Test
    public void testGetProductByIdNotFound() {
        //given
        Long id = 1L;
        Mockito.when(productRepository.findById(id)).thenReturn(Optional.empty());

        //when
        Optional<Product> optionalProduct = productService.getProductById(id);

        //then
        assertFalse(optionalProduct.isPresent());

        Mockito.verify(productRepository).findById(id);
    }
}