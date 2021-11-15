package com.rookies.nashtech.ShoppingCart.mapper;

import com.rookies.nashtech.ShoppingCart.dto.OrderDTO;
import com.rookies.nashtech.ShoppingCart.entity.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {
    public OrderDTO fromEntity(Order order) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(order.getId());
        orderDTO.setUserName(order.getUser().getUsername());
        orderDTO.setDateCreated(order.getDateCreated());
        orderDTO.setTotalCost(order.getTotalCost());
        orderDTO.setState(order.getState());
        return orderDTO;
    }
}
