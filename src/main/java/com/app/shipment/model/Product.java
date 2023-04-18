package com.app.shipment.model;

public class Product {
    private Long id;
    private String name;
    private String SKU;
    private int quantity;
    private float weight;
    private float price;
    //many-to-one
    private Warehouse warehouse;
}
