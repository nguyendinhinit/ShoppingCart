package com.rookies.nashtech.ShoppingCart.controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.rookies.nashtech.ShoppingCart.dto.OrderDTO;
import com.rookies.nashtech.ShoppingCart.payload.OrderPayload;
import com.rookies.nashtech.ShoppingCart.service.OrderService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;

@RestController
@RequestMapping("/api/v1/order")
public class OrderController {
  private final Logger logger = LoggerFactory.getLogger(OrderController.class);
  private final OrderService orderService;

  public OrderController(OrderService orderService) {
    this.orderService = orderService;
  }

  @ApiOperation(value = "Get all Orders", authorizations = {@Authorization(value = "jwtToken")}, response = OrderDTO.class)
  @GetMapping(value = "/")
  public ResponseEntity<List<OrderDTO>> getAllOrders() {
    List<OrderDTO> orders = orderService.getAllOrders();
    return ResponseEntity.ok(orders);
  }

  @ApiOperation(value = "Get an Order by its ID", authorizations = {@Authorization(value = "jwtToken")}, response = OrderDTO.class)
  @GetMapping(value = "/{orderId}")
  public ResponseEntity<OrderDTO> getOrderById(@PathVariable Integer orderId) {
    OrderDTO order = orderService.getOrderById(orderId);
    return ResponseEntity.ok(order);
  }

  @ApiOperation(value = "Pay an Order by its ID", authorizations = {@Authorization(value = "jwtToken")}, response = OrderDTO.class)
  @PutMapping(value = "/payment/{orderId}")
  public ResponseEntity<OrderDTO> payOrderById(@PathVariable Integer orderId) {
    OrderDTO order = orderService.payOrderById(orderId);
    return ResponseEntity.ok(order);
  }

  @ApiOperation(value = "create an order", authorizations = {@Authorization(value = "jwtToken")}, response = OrderDTO.class)
  @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<OrderDTO> createOrder(@RequestBody OrderPayload payload) {
    logger.warn("Payload {}", payload.getProducts());
    return ResponseEntity.ok(new OrderDTO());
  }
}
