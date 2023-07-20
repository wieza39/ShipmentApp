package com.app.shipment.service;

import com.app.shipment.exceptions.Duplicate;
import com.app.shipment.exceptions.EmptyList;
import com.app.shipment.exceptions.ProductNotFound;
import com.app.shipment.exceptions.WarehouseNotFound;
import com.app.shipment.model.Product;
import com.app.shipment.warehouse.model.Warehouse;
import com.app.shipment.repository.ProductRepository;
import com.app.shipment.warehouse.service.WarehouseService;
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
    private ProductRepository productRepository;

    @Mock
    private WarehouseService warehouseService;

    @InjectMocks
    private ProductServiceClient productService;


//    @Test
//    void shouldReturnProductByGivenId() {
//        //given
//        Long id = 1L;
//        Warehouse warehouse = new Warehouse();
//        Product product = new Product(id, "test name", "A123", 5,4.0f, 50.0f, warehouse);
//        Mockito.when(productRepository.findById(id)).thenReturn(Optional.of(product));
//
//        //when
//        Optional<Product> optionalProduct = productService.getProductById(id);
//
//        //then
//        assertTrue(optionalProduct.isPresent());
//        assertEquals(product, optionalProduct.get());
//
//        Mockito.verify(productRepository).findById(id);
//    }
//
//    @Test
//    void shouldFailToFindProduct() {
//        //given
//        Long id = 1L;
//        Mockito.when(productRepository.findById(id)).thenReturn(Optional.empty());
//
//        //when
//        Optional<Product> optionalProduct = productService.getProductById(id);
//
//        //then
//        assertFalse(optionalProduct.isPresent());
//
//        Mockito.verify(productRepository).findById(id);
//    }
//
//    @Test
//    void shouldThrowProductNotFoundException() {
//        Long id = 123L;
//        Mockito.when(productRepository.findById(id)).thenReturn(Optional.empty());
//
//        assertThrows(ProductNotFound.class, () -> productService.getProductById(id));
//    }
//
////    @Test
////    void shouldFindProductBySku() {
////        String sku = "SKU-test";
////        Product product = new Product(1L, "available", sku, 5,4.0f, 50.0f, new Warehouse());
////        Mockito.when(productRepository.findProductBySku(sku)).thenReturn(Optional.of(product));
////
////        Optional<Product> skuProduct = productService.getProductBySku(sku);
////
////        assertTrue(skuProduct.isPresent());
////        assertEquals(skuProduct.get(), product);
////    }
//
////    @Test
////    void shouldThrowExceptionIfSkuNotFound() {
////        String sku = "SKU-test";
////        Mockito.when(productRepository.findProductBySku(sku)).thenReturn(Optional.empty());
////
////        assertThrows(ProductNotFound.class, () -> productService.getProductBySku(sku));
////    }
//
//    @Test
//    void shouldReturnAvailableProduct() {
//        //given
//        Product product1 = new Product(1L, "available", "A123", 5,4.0f, 50.0f, new Warehouse());
//        Product product2 = new Product(2L, "unavailable", "B456", 0,4.0f, 50.0f, new Warehouse());
//        List<Product> list = new ArrayList<>();
//        list.add(product1);
//        list.add(product2);
//        Mockito.when(productRepository.findAll()).thenReturn(list);
//        //when
//        List<Product> available = productService.getAvailableProducts();
//        //then
//        assertEquals(product1, available.get(0));
//        assertFalse(available.contains(product2));
//    }
//
//    @Test
//    void shouldThrowEmptyListForAvailableProducts() {
//        //given
//        Product product1 = new Product(1L, "available", "A123", 0,4.0f, 50.0f, new Warehouse());
//        Product product2 = new Product(2L, "unavailable", "B456", 0,4.0f, 50.0f, new Warehouse());
//        List<Product> list = new ArrayList<>();
//        list.add(product1);
//        list.add(product2);
//        Mockito.when(productRepository.findAll()).thenReturn(list);
//        //when-then
//        assertThrows(EmptyList.class, () -> productService.getAvailableProducts());
//    }
//
//    @Test
//    void shouldReturnUnavailableProduct() {
//        //given
//        Product product1 = new Product(1L, "available", "A123", 5,4.0f, 50.0f, new Warehouse());
//        Product product2 = new Product(2L, "unavailable", "B456", 0,4.0f, 50.0f, new Warehouse());
//        List<Product> list = new ArrayList<>();
//        list.add(product1);
//        list.add(product2);
//        Mockito.when(productRepository.findAll()).thenReturn(list);
//        //when
//        List<Product> available = productService.getUnavailableProducts();
//        //then
//        assertEquals(product2, available.get(0));
//        assertFalse(available.contains(product1));
//    }
//
//    @Test
//    void shouldThrowEmptyListForUnavailableProducts() {
//        //given
//        Product product1 = new Product(1L, "available", "A123", 5,4.0f, 50.0f, new Warehouse());
//        Product product2 = new Product(2L, "unavailable", "B456", 3,4.0f, 50.0f, new Warehouse());
//        List<Product> list = new ArrayList<>();
//        list.add(product1);
//        list.add(product2);
//        Mockito.when(productRepository.findAll()).thenReturn(list);
//        //when-then
//        assertThrows(EmptyList.class, () -> productService.getUnavailableProducts());
//    }
//
//    @Test
//    void addProduct() {
//        String location = "Gdansk";
//        Warehouse warehouse = new Warehouse();
//        warehouse.setLocation(location);
//        Product pattern = new Product(1L, "available", "A123", 5, 4.0f, 50.0f, warehouse);
//
//        Mockito.when(warehouseService.getWarehouseByLocation(location)).thenReturn(Optional.of(warehouse));
//
//        Product productFromMethod = productService.addNewProduct(pattern, location);
//
//        assertEquals(pattern, productFromMethod);
//        Mockito.verify(productRepository).save(pattern);
//    }
//
//    @Test
//    void shouldThrowDuplicateExceptionWhenAddingProduct() {
//        Warehouse warehouse = new Warehouse();
//        String location = "Gdansk";
//        String sku = "A123";
//        warehouse.setLocation(location);
//        Product productPattern = new Product(1L, "available", sku, 5, 4.0f, 50.0f, warehouse);
//
//        Mockito.when(warehouseService.getWarehouseByLocation(location)).thenReturn(Optional.of(warehouse));
//        Mockito.when(productRepository.existsBySku(sku)).thenReturn(true);
//
//        assertThrows(Duplicate.class, () -> productService.addNewProduct(productPattern, location));
//    }
//
//    @Test
//    void shouldFailAddingProductWhenLocationNotFound() {
//        Warehouse warehouse = new Warehouse();
//        String location = "Gdansk";
//        warehouse.setLocation(location);
//        Product productPattern = new Product(1L, "available", "A123", 5, 4.0f, 50.0f, warehouse);
//
//        Mockito.when(warehouseService.getWarehouseByLocation(location)).thenReturn(Optional.empty());
//
//        assertThrows(WarehouseNotFound.class, () -> productService.addNewProduct(productPattern, location));
//    }
}