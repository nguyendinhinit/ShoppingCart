package com.rookies.nashtech.ShoppingCart.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "order_products")
public class OrderProduct {
  @Id
  @Column(name = "order_id")
  private int id;
  @ManyToOne
  @JoinColumn(name = "product_id")
  private Product product;
  @Column(name = "quantity")
  private int quantity;

}
