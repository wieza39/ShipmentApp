package com.app.shipment.ordermanagment.service;

import com.app.shipment.ordermanagment.config.WebClientBuild;
import com.app.shipment.ordermanagment.exceptions.ProductNotFoundException;
import com.app.shipment.ordermanagment.model.CustomerDTO;
import com.app.shipment.ordermanagment.model.OrderConfirmDTO;
import com.app.shipment.ordermanagment.model.OrderDTO;
import com.app.shipment.ordermanagment.model.ProductInfoResponse;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class OrderManageService {

    private final WebClientBuild inventoryWebclient;
    private final WebClientBuild customerWebclient;
    private final WebClientBuild orderWebclient;

    public OrderManageService(WebClientBuild inventoryWebclient, WebClientBuild customerWebclient, WebClientBuild orderWebclient) {
        this.inventoryWebclient = inventoryWebclient;
        this.customerWebclient = customerWebclient;
        this.orderWebclient = orderWebclient;
    }

    public OrderConfirmDTO order(OrderDTO orderDTO) {
        OrderConfirmDTO orderConfirmDTO = new OrderConfirmDTO();
        

    }
    //create order (requires:
    //inventory-service: check availability + change quantity)
    //order-service: post order

    public void withdrawProduct(String sku, int quantity) {
        inventoryWebclient.inventoryWebClient()
                .method(HttpMethod.GET)
                .uri(uriBuilder -> uriBuilder
                        .path("api/product/withdraw")
                        .queryParam("sku", sku, "quantity", quantity).build())
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError,
                        error -> Mono.error(new ProductNotFoundException("SKU: " + sku + " is incorrect or doesn't exist")))
                .bodyToMono(ProductInfoResponse.class)
                .block();
    }

    public void checkStatus() {}

    /** Gets product info from Inventory Service.
     * Not really useful for now, just for testing connection and answer matters */
    public ProductInfoResponse getProductBySku(String sku) {
        return inventoryWebclient.inventoryWebClient()
                .method(HttpMethod.GET)
                .uri(uriBuilder -> uriBuilder
                        .path("/api/product/sku").
                        queryParam("sku", sku).build())
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError,
                        error -> Mono.error(new ProductNotFoundException("SKU: " + sku + " is incorrect or doesn't exist")))
                .bodyToMono(ProductInfoResponse.class)
                .block();
    }

    /** Gets client's delivery details from Customer Service */
    public CustomerDTO getCustomerDeliveryDetails(String login) {
        return inventoryWebclient.customerWebClient()
                .method(HttpMethod.GET)
                .uri(uriBuilder -> uriBuilder
                        .path("/api/customers/deliveryDetails")
                        .queryParam("login", login).build())
                .retrieve()
                .bodyToMono(CustomerDTO.class)
                .block();
    }
}
