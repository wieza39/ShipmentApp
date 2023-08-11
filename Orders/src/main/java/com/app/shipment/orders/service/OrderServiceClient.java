package com.app.shipment.orders.service;

import com.app.shipment.orders.model.dto.OrderDTO;
import com.app.shipment.orders.model.dto.OrderResponse;
import com.app.shipment.orders.model.entity.Order;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceClient {

    private final OrderService orderService;

    public OrderServiceClient(OrderService orderService) {
        this.orderService = orderService;
    }

    public OrderResponse createOrder(OrderDTO orderDTO) {
        Order order = orderService.addOrder(orderDTO);

        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setOrderNumber(order.getOrderNumber());
        orderResponse.setDeliveryDate(order.getDeliveryDate());
        orderResponse.setCustomerDTO(orderDTO.getCustomerDTO());
        orderResponse.setOrderedProductDTOList(orderDTO.getOrderedProductList());

        return orderResponse;
    }
}
