package com.rookies.nashtech.ShoppingCart.mapper;

import com.rookies.nashtech.ShoppingCart.dto.OrderProductDTO;
import com.rookies.nashtech.ShoppingCart.payload.OrderPayload;
import com.rookies.nashtech.ShoppingCart.payload.ProductPayload;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderProductMapper {
  public List<OrderProductDTO> fromPayload(OrderPayload payload) {
    List<OrderProductDTO> orderProductList = new ArrayList<>();
    List<ProductPayload> products = payload.getProducts();
    for (ProductPayload product : products) {
      OrderProductDTO orderProductDTO = new OrderProductDTO();
      orderProductDTO.setOrderId(payload.getOrderId());
      orderProductDTO.setProductId(product.getId());
      orderProductDTO.setQuantity(product.getQuantity());
      orderProductList.add(orderProductDTO);
    }
    return orderProductList;
  }
}
