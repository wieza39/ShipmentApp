package com.app.shipment.orders.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderedProductDTO {
    private String sku;
    private int quantity;
    private float price;
}
