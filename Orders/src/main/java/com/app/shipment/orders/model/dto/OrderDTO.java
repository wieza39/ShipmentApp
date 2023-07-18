package com.app.shipment.orders.model.dto;

import com.app.shipment.orders.model.OrderedProduct;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
    private List<OrderedProduct> orderedProductList;
    private CustomerDTO customerDTO;
}
