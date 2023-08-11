package com.app.shipment.ordermanagment.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderConfirmDTO {
    private String orderNumber;
    private LocalDate deliveryDate;
}
