package com.rookies.nashtech.ShoppingCart.repository;

import com.rookies.nashtech.ShoppingCart.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
  Order findOrderById(int orderId);
}
