package com.app.shipment.ordermanagment.controller;

import com.app.shipment.ordermanagment.model.AddressDTO;
import com.app.shipment.ordermanagment.model.CustomerDTO;
import com.app.shipment.ordermanagment.model.OrderConfirmDTO;
import com.app.shipment.ordermanagment.model.OrderDTO;
import com.app.shipment.ordermanagment.model.ProductInfoResponse;
import com.app.shipment.ordermanagment.service.OrderManageService;
import org.springframework.boot.autoconfigure.amqp.RabbitConnectionDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @PostMapping("/order/new")
    public ResponseEntity<OrderConfirmDTO> addOrder(@RequestBody OrderDTO orderDTO) {
        OrderConfirmDTO orderConfirm = orderManageService.order(orderDTO);
        return ResponseEntity.ok(orderConfirm);
    }

    @PostMapping("customer/address/new")
    public ResponseEntity<Void> addAddress(@RequestBody AddressDTO address, @RequestParam String login) {
        orderManageService.addNewAddress(address, login);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
