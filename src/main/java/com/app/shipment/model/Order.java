package com.app.shipment.model;

import java.time.LocalDate;
import java.util.List;

public class Order {
    private Long id;
    private int quantity;
    //one-to-many
    private List<OrderedProduct> orderList;
    private String orderNumber;
    private LocalDate creationDate;
    private LocalDate deliveryDate;
    //many-to-one
    private User orderOwner;
    private Address deliveryAddress;
    private OrderStatus orderStatus;
}
