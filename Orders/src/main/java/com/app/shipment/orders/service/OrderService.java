package com.app.shipment.orders.service;

import com.app.shipment.orders.model.Order;
import com.app.shipment.orders.model.OrderedProduct;
import com.app.shipment.orders.model.dto.AddressDTO;
import com.app.shipment.orders.model.dto.CustomerDTO;
import com.app.shipment.orders.model.dto.OrderDTO;
import com.app.shipment.orders.model.dto.OrderedProductDTO;
import com.app.shipment.orders.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public String generateOrderNumber() {

    }

    public Order addOrder(OrderDTO orderDTO) {
        Order newOrder = new Order();
        List<OrderedProduct> orderedProductList;

        CustomerDTO customerData = new CustomerDTO();
        AddressDTO addressData = new AddressDTO();
        List<OrderedProductDTO> orderedProductDTOList = new ArrayList<>();


        addressData.setStreet(orderDTO.getCustomerDTO().getAddress().getStreet());
        addressData.setBuilding(orderDTO.getCustomerDTO().getAddress().getBuilding());
        addressData.setFlat(orderDTO.getCustomerDTO().getAddress().getFlat());
        addressData.setPostalCode(orderDTO.getCustomerDTO().getAddress().getPostalCode());
        addressData.setCity(orderDTO.getCustomerDTO().getAddress().getCity());
        addressData.setCountry(orderDTO.getCustomerDTO().getAddress().getCountry());

        customerData.setName(orderDTO.getCustomerDTO().getName());
        customerData.setSurname(orderDTO.getCustomerDTO().getSurname());
        customerData.setEmail(orderDTO.getCustomerDTO().getEmail());
        customerData.setPhone(orderDTO.getCustomerDTO().getPhone());
        customerData.setAddress(addressData);

        orderedProductList = orderDTO.getOrderedProductList();

        newOrder.setOrderList(orderedProductList);

        return newOrder;
    }
}