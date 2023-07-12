package com.app.shipment.ordermanagment.service;

import com.app.shipment.ordermanagment.config.WebClientBuild;
import com.app.shipment.ordermanagment.model.ProductInfoResponse;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

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
                .bodyToMono(ProductInfoResponse.class)
                .block();
    }
}
