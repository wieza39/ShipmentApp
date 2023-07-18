package com.app.shipment.ordermanagment.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDeliveryDetails {
    private String name;
    private String surname;
    private String phone;
    private String email;
    private AddressDTO address;
}