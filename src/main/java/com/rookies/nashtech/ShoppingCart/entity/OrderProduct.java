package com.rookies.nashtech.ShoppingCart.entity;

import javax.persistence.*;

@Entity
@Table(name = "orders_products")
public class OrderProduct {
  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  @ManyToOne
  @JoinColumn(name = "product_id")
  private Product product;
  @ManyToOne
  @JoinColumn(name = "order_id")
  private Order order;
  @Column(name = "quantity")
  private Integer quantity;

}
