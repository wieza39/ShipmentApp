package com.app.shipment.orders.service;

import org.springframework.stereotype.Service;

@Service
public class OrderServiceClient {

    private final OrderService orderService;

    public OrderServiceClient(OrderService orderService) {
        this.orderService = orderService;
    }


}
