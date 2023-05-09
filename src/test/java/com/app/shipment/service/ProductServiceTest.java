package com.app.shipment.service;

import com.app.shipment.exceptions.EmptyList;
import com.app.shipment.exceptions.ProductNotFound;
import com.app.shipment.model.Product;
import com.app.shipment.model.Warehouse;
import com.app.shipment.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    ProductRepository productRepository;

    @InjectMocks
    ProductService productService;

    @Test
    void shouldReturnProductByGivenId() {
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
    void shouldFailToFindProduct() {
        //given
        Long id = 1L;
        Mockito.when(productRepository.findById(id)).thenReturn(Optional.empty());

        //when
        Optional<Product> optionalProduct = productService.getProductById(id);

        //then
        assertFalse(optionalProduct.isPresent());

        Mockito.verify(productRepository).findById(id);
    }

    @Test
    void shouldThrowProductNotFoundException() {
        Long id = 123L;
        Mockito.when(productRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ProductNotFound.class, () -> productService.getProductById(id));
    }

    @Test
    void shouldFindProductBySku() {}

    @Test
    void shouldThrowExceptionIfSkuNotFound() {}

    @Test
    void shouldReturnAvailableProduct() {
        //given
        Product product1 = new Product(1L, "available", "A123", 5,4.0f, 50.0f, new Warehouse());
        Product product2 = new Product(2L, "unavailable", "B456", 0,4.0f, 50.0f, new Warehouse());
        List<Product> list = new ArrayList<>();
        list.add(product1);
        list.add(product2);
        Mockito.when(productRepository.findAll()).thenReturn(list);
        //when
        List<Product> available = productService.getAvailableProducts();
        //then
        assertEquals(product1, available.get(0));
        assertFalse(available.contains(product2));
    }

    @Test
    void shouldThrowEmptyListForAvailableProducts() {
        //given
        Product product1 = new Product(1L, "available", "A123", 0,4.0f, 50.0f, new Warehouse());
        Product product2 = new Product(2L, "unavailable", "B456", 0,4.0f, 50.0f, new Warehouse());
        List<Product> list = new ArrayList<>();
        list.add(product1);
        list.add(product2);
        Mockito.when(productRepository.findAll()).thenReturn(list);
        //when-then
        assertThrows(EmptyList.class, () -> productService.getAvailableProducts());
    }

    @Test
    void shouldReturnUnavailableProduct() {
        //given
        Product product1 = new Product(1L, "available", "A123", 5,4.0f, 50.0f, new Warehouse());
        Product product2 = new Product(2L, "unavailable", "B456", 0,4.0f, 50.0f, new Warehouse());
        List<Product> list = new ArrayList<>();
        list.add(product1);
        list.add(product2);
        Mockito.when(productRepository.findAll()).thenReturn(list);
        //when
        List<Product> available = productService.getUnavailableProducts();
        //then
        assertEquals(product2, available.get(0));
        assertFalse(available.contains(product1));
    }

    @Test
    void shouldThrowEmptyListForUnavailableProducts() {
        //given
        Product product1 = new Product(1L, "available", "A123", 5,4.0f, 50.0f, new Warehouse());
        Product product2 = new Product(2L, "unavailable", "B456", 3,4.0f, 50.0f, new Warehouse());
        List<Product> list = new ArrayList<>();
        list.add(product1);
        list.add(product2);
        Mockito.when(productRepository.findAll()).thenReturn(list);
        //when-then
        assertThrows(EmptyList.class, () -> productService.getUnavailableProducts());
    }

    @Test
    void addProduct() {}
}