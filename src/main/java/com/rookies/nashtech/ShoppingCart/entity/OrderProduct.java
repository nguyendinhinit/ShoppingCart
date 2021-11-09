package com.rookies.nashtech.ShoppingCart.entity;

import javax.persistence.*;

@Entity
@Table(name = "order_products")
public class OrderProduct {
  @Id
  @Column(name = "order_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  @ManyToOne
  @JoinColumn(name = "product_id")
  private Product product;
  @Column(name = "quantity")
  private Integer quantity;

}
