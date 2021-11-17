package com.rookies.nashtech.ShoppingCart.controller;

import com.rookies.nashtech.ShoppingCart.dto.OrderDTO;
import com.rookies.nashtech.ShoppingCart.payload.OrderPayload;
import com.rookies.nashtech.ShoppingCart.service.OrderService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.Authorization;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/order")
public class OrderController {
  private final Logger logger = LoggerFactory.getLogger(OrderController.class);
  private final OrderService orderService;

  public OrderController(OrderService orderService) {
    this.orderService = orderService;
  }

  @ApiOperation(value = "Get all Orders", response = OrderDTO.class, authorizations = {@Authorization(value = "jwtToken")})
  @ApiResponse(code = 200, message = "OK")
  @GetMapping(value = "/all")
  public ResponseEntity<List<OrderDTO>> getAllOrders() {
    logger.info("Get all Order");
    List<OrderDTO> orders = orderService.getAllOrders();
    return ResponseEntity.ok(orders);
  }

  @ApiOperation(value = "Get an Order by Order ID", response = OrderDTO.class, authorizations = {@Authorization(value = "jwtToken")})
  @ApiResponse(code = 200, message = "OK")
  @GetMapping(value = "/{orderId}")
  public ResponseEntity<OrderDTO> getOrderById(@PathVariable Integer orderId) {
    logger.info("Get Order by ID {}", orderId);
    OrderDTO order = orderService.getOrderById(orderId);
    return ResponseEntity.ok(order);
  }

  @ApiOperation(value = "Pay an Order by Order ID", response = OrderDTO.class, authorizations = {@Authorization(value = "jwtToken")})
  @ApiResponse(code = 200, message = "Order paid successfully")
  @PutMapping(value = "/payment/{orderId}")
  public ResponseEntity<OrderDTO> payOrderById(@PathVariable Integer orderId) {
    logger.info("Pay an Order by ID: {}", orderId);
    OrderDTO order = orderService.payOrderById(orderId);
    return ResponseEntity.ok(order);
  }

  @ApiOperation(value = "Create an Order", response = OrderDTO.class, authorizations = {@Authorization(value = "jwtToken")})
  @ApiResponse(code = 201, message = "Order created")
  @PostMapping(
          value = "/",
          consumes = MediaType.APPLICATION_JSON_VALUE,
          produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<OrderDTO> createOrder(@RequestBody OrderPayload payload) {
    logger.info("Create an Order");
    OrderDTO orderDTO = orderService.createOrder(payload);
    return ResponseEntity.ok(orderDTO);
  }

  @ApiOperation(value = "Delete an Order by Order ID", authorizations = {@Authorization(value = "jwtToken")})
  @ApiResponse(code = 200, message = "Order deleted")
  @DeleteMapping(value = "/{orderId}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> deleteOrder(@PathVariable Integer orderId) {
    logger.info("Delete an Order by ID {}", orderId);
    orderService.deleteOrderById(orderId);
    return ResponseEntity.ok().build();
  }
}
