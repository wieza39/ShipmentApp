package com.app.shipment.ordermanagment.service;

import com.app.shipment.ordermanagment.config.WebClientBuild;
import com.app.shipment.ordermanagment.exceptions.ProductNotFoundException;
import com.app.shipment.ordermanagment.model.AddressDTO;
import com.app.shipment.ordermanagment.model.CustomerDeliveryDetails;
import com.app.shipment.ordermanagment.model.ProductInfoResponse;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
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

    public ProductInfoResponse getProductBySku(String sku) {
        return inventoryWebclient.inventoryWebClient()
                .method(HttpMethod.GET)
                .uri(uriBuilder -> uriBuilder
                        .path("/product/sku").
                        queryParam("sku", sku).build())
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError,
                        error -> Mono.error(new ProductNotFoundException("SKU: " + sku + " is incorrect or doesn't exist")))
                .bodyToMono(ProductInfoResponse.class)
                .block();
    }

    //get client delivery details
    public CustomerDeliveryDetails getCustomerDeliveryDetails(String login) {
        return inventoryWebclient.inventoryWebClient()
                .method(HttpMethod.GET)
                .uri(uriBuilder -> uriBuilder
                        .path("/customers/deliveryDetails")
                        .queryParam("login", login).build())
                .retrieve()
                .bodyToMono(CustomerDeliveryDetails.class)
                .block();
    }

    //create order
}
