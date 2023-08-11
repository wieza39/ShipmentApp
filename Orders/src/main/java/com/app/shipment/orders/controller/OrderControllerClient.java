package com.app.shipment.orders.controller;

import com.app.shipment.orders.model.dto.OrderDTO;
import com.app.shipment.orders.model.dto.OrderResponse;
import com.app.shipment.orders.service.OrderServiceClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
public class OrderControllerClient {

    private final OrderServiceClient orderServiceClient;

    public OrderControllerClient(OrderServiceClient orderServiceClient) {
        this.orderServiceClient = orderServiceClient;
    }

    @PostMapping("/new")
    public ResponseEntity<OrderResponse> createOrder(@RequestBody OrderDTO orderDTO) {
        OrderResponse orderResponse = orderServiceClient.createOrder(orderDTO);
        return ResponseEntity.ok(orderResponse);
    }
}
