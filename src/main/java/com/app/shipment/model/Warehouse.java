package com.app.shipment.model;

import java.util.List;

public class Warehouse {
    private Long id;
    //one-to-many
    private List<Product> stock;
    private String location;
}
