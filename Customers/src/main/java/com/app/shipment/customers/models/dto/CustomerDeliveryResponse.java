package com.app.shipment.customers.models.dto;

import com.app.shipment.customers.models.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDeliveryResponse {
    private String name;
    private String surname;
    private String phone;
    private String email;
    private Address address;
}
