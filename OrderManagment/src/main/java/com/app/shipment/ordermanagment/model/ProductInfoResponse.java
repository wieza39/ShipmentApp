package com.app.shipment.ordermanagment.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductInfoResponse {
    private String sku;
    private int quantity;
    private float price;
    private float weight;
}
