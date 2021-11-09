package com.rookies.nashtech.ShoppingCart.controller;

import com.rookies.nashtech.ShoppingCart.dto.OrderDTO;
import com.rookies.nashtech.ShoppingCart.service.OrderService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/order")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @ApiOperation(value = "Get all Orders", response = OrderDTO.class)
    @GetMapping(value = "/")
    public ResponseEntity<List<OrderDTO>> getAllOrders() {
        List<OrderDTO> orders = orderService.getAllOrders();
        return ResponseEntity.ok(orders);
    }

    @ApiOperation(value = "Get an Order by its ID", response = OrderDTO.class)
    @GetMapping(value = "/{orderId}")
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable Integer orderId) {
        OrderDTO order = orderService.getOrderById(orderId);
        return ResponseEntity.ok(order);
    }

    @ApiOperation(value = "Pay an Order by its ID", response = OrderDTO.class)
    @PutMapping(value = "/{orderId}")
    public ResponseEntity<OrderDTO> payOrderById(@PathVariable Integer orderId) {
        OrderDTO order = orderService.payOrder(orderId);
        return ResponseEntity.ok(order);
    }
}
