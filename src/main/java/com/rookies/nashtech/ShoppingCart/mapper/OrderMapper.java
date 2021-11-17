package com.rookies.nashtech.ShoppingCart.mapper;

import com.rookies.nashtech.ShoppingCart.dto.OrderDTO;
import com.rookies.nashtech.ShoppingCart.entity.Order;
import com.rookies.nashtech.ShoppingCart.payload.OrderPayload;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {
  public OrderDTO fromEntity(Order order) {
    OrderDTO orderDTO = new OrderDTO();
    orderDTO.setId(order.getId());
    orderDTO.setUsername(order.getUser().getUsername());
    orderDTO.setDateCreated(order.getDateCreated());
    orderDTO.setTotalCost(order.getTotalCost());
    orderDTO.setState(order.getState());
    return orderDTO;
  }

  public OrderDTO fromPayload(OrderPayload payload) {
    OrderDTO orderDTO = new OrderDTO();
    orderDTO.setUsername(payload.getUsername());
    orderDTO.setDateCreated(payload.getDateCreated());
    orderDTO.setTotalCost(payload.getTotalCost());
    orderDTO.setState(payload.getState());
    return orderDTO;
  }

  public Order fromDTO(OrderDTO orderDTO) {
    Order order = new Order();
    order.setDateCreated(orderDTO.getDateCreated());
    order.setTotalCost(orderDTO.getTotalCost());
    order.setState(orderDTO.getState());
    return order;
  }


}
