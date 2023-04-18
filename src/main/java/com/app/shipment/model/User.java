package com.app.shipment.model;

import java.util.List;

public class User {
    private Long id;
    private String name;
    private String phone;
    //one-to-many
    private List<Address> addressList;
    //one-to-many
    private Order order;
}
