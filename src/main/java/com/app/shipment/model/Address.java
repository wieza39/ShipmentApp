package com.app.shipment.model;

public class Address {
    private Long id;
    private String street;
    private String building;
    private String flat;
    private String postalCode;
    private String city;
    private String country;
    //many-to-one
    private User user;
}
