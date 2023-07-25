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
        orderNumber = yearStr + monthStr + dayStr + "/" + initials;


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

        orderRepository.save(newOrder);

        return newOrder;

//        CustomerDTO customerData = new CustomerDTO();
//        AddressDTO addressData = new AddressDTO();
//
//
//        addressData.setStreet(orderDTO.getCustomerDTO().getAddress().getStreet());
//        addressData.setBuilding(orderDTO.getCustomerDTO().getAddress().getBuilding());
//        addressData.setFlat(orderDTO.getCustomerDTO().getAddress().getFlat());
//        addressData.setPostalCode(orderDTO.getCustomerDTO().getAddress().getPostalCode());
//        addressData.setCity(orderDTO.getCustomerDTO().getAddress().getCity());
//        addressData.setCountry(orderDTO.getCustomerDTO().getAddress().getCountry());
//
//        customerData.setName(orderDTO.getCustomerDTO().getName());
//        customerData.setSurname(orderDTO.getCustomerDTO().getSurname());
//        customerData.setEmail(orderDTO.getCustomerDTO().getEmail());
//        customerData.setPhone(orderDTO.getCustomerDTO().getPhone());
//        customerData.setAddress(addressData);
    }
}
