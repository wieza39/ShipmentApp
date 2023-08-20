package com.app.shipment.ordermanagment.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {
    private String orderNumber;
    private LocalDate deliveryDate;
    private List<OrderedProductDTO> orderedProductDTOList;
    private CustomerDTO customerDTO;
}
