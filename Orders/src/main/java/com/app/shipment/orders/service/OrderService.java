package com.app.shipment.orders.service;

import com.app.shipment.orders.model.dto.OrderedProductDTO;
import com.app.shipment.orders.model.entity.Order;
import com.app.shipment.orders.model.entity.OrderStatus;
import com.app.shipment.orders.model.entity.OrderedProduct;
import com.app.shipment.orders.model.dto.AddressDTO;
import com.app.shipment.orders.model.dto.CustomerDTO;
import com.app.shipment.orders.model.dto.OrderDTO;
import com.app.shipment.orders.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public String generateOrderNumber(CustomerDTO customerData) {
        String orderNumber = "";

        String name = customerData.getName();
        String surname = customerData.getSurname();
        LocalDate date = LocalDate.now();

        int year = date.getYear();
        int month = date.getMonthValue();
        int day = date.getDayOfMonth();

        // Convert date to String
        String yearStr = String.format("%04d", year);
        String monthStr = String.format("%02d", month);
        String dayStr = String.format("%02d", day);

        String initials = name.substring(0, 3).toUpperCase() + surname.substring(0, 3).toUpperCase();

        long orderSeqNr = getAllOrders().stream().count() + 1;
        orderNumber = yearStr + monthStr + dayStr + "000" + orderSeqNr + "/" + initials;


        return orderNumber;
    }

    public Order addOrder(OrderDTO orderDTO) {
        List<OrderedProductDTO> orderedProductDTOList = orderDTO.getOrderedProductList();

        Order newOrder = new Order();
        List<OrderedProduct> orderedProductList = new ArrayList<>();

        for(OrderedProductDTO productDTO : orderedProductDTOList) {
            OrderedProduct product = new OrderedProduct();
            product.setQuantity(productDTO.getQuantity());
            product.setSku(productDTO.getSku());
            product.setPrice(productDTO.getPrice() * product.getQuantity());

            orderedProductList.add(product);
        }

        CustomerDTO customerData = orderDTO.getCustomerDTO();

        String orderNumber = generateOrderNumber(customerData);
        newOrder.setOrderNumber(orderNumber);
        newOrder.setOrderStatus(OrderStatus.PENDING);
        newOrder.setOrderList(orderedProductList);

        LocalDate deliveryDate = LocalDate.now();
        newOrder.setDeliveryDate(deliveryDate.plusDays(2));

        orderRepository.save(newOrder);

        return newOrder;

    }
}
