package com.rookies.nashtech.ShoppingCart.repository;

import com.rookies.nashtech.ShoppingCart.entity.Order;
import com.rookies.nashtech.ShoppingCart.entity.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderProductRepository extends JpaRepository<OrderProduct, Integer> {
  void deleteAllByOrder(Order order);

  List<OrderProduct> findAllByOrder(Order order);
}
