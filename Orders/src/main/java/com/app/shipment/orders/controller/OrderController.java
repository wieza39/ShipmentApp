package com.app.shipment.orders.controller;

import com.app.shipment.orders.model.entity.Order;
import com.app.shipment.orders.model.dto.OrderDTO;
import com.app.shipment.orders.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> orderList = orderService.getAllOrders();
        return ResponseEntity.ok(orderList);
    }

    @PostMapping("/new")
    public ResponseEntity<Order> addNewOrder(@RequestBody OrderDTO orderDTO) {
        Order newOrder = orderService.addOrder(orderDTO);
        return ResponseEntity.ok(newOrder);
    }


}
