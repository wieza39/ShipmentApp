package com.app.shipment.orders.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
    private List<OrderedProductDTO> orderedProductList;
    private CustomerDTO customerDTO;
}
