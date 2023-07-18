package com.app.shipment.orders.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {
    private String name;
    private String surname;
    private String phone;
    private String email;
    private AddressDTO address;
}
