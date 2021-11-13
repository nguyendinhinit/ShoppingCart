package com.rookies.nashtech.ShoppingCart.controller;

import com.rookies.nashtech.ShoppingCart.service.PaymentService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class PaymentController {
    /**
      state of order
     <pre>
      0 - Order
      1 - Deliver
      2 - Delivered
      3 - Completed - just customer can confirm, after 3 day of state delivered if customer doesn't confirm yet,
       state will automatically change to complete
     </pre>
     */
    private final Logger logger = LoggerFactory.getLogger(PaymentController.class);
    private final PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @ApiOperation(value = "Customer confirm payment")
    @PutMapping(value = "/payment/complete")
    public ResponseEntity<Void> CompleteOrder(@RequestParam(value = "id") Integer id) {
        paymentService.completeOrder(id);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "Shipper confirm payment")
    @PutMapping(value = "/payment/delivered")
    public ResponseEntity<Void> ConfirmOrderDelivered(@RequestParam(value = "id") Integer id) {
        paymentService.deliveredOrder(id);
        return ResponseEntity.ok().build();
    }

}