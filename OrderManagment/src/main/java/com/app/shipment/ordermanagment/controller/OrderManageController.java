package com.app.shipment.ordermanagment.controller;

import com.app.shipment.ordermanagment.model.CustomerDTO;
import com.app.shipment.ordermanagment.model.ProductInfoResponse;
import com.app.shipment.ordermanagment.service.OrderManageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/manage")
public class OrderManageController {

    private final OrderManageService orderManageService;

    public OrderManageController(OrderManageService orderManageService) {
        this.orderManageService = orderManageService;
    }



    @GetMapping("/findbysku")
    public ResponseEntity<ProductInfoResponse> getProductBySku(@RequestParam String sku) {
        ProductInfoResponse product = orderManageService.getProductBySku(sku);
        return ResponseEntity.ok(product);
    }

    @GetMapping("/findCustomerDeliveryDetails")
    public ResponseEntity<CustomerDTO> getCustomerDeliveryDetails(@RequestParam String login) {
        CustomerDTO customerDetails = orderManageService.getCustomerDeliveryDetails(login);
        return ResponseEntity.ok(customerDetails);
    }


}
