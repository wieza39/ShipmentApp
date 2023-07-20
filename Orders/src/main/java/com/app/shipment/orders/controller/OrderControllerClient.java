package com.app.shipment.orders.controller;

import com.app.shipment.orders.service.OrderServiceClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
public class OrderControllerClient {

    private final OrderServiceClient orderServiceClient;

    public OrderControllerClient(OrderServiceClient orderServiceClient) {
        this.orderServiceClient = orderServiceClient;
    }


}
