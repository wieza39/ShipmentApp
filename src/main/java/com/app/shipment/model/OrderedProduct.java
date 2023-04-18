package com.app.shipment.model;

public class OrderedProduct {
    private Long id;
    private int quantity;
    private float price;
    //many-to-one
    private Order order;
    //many-to-one
    private Product product;
}
