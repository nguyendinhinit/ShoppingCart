package com.rookies.nashtech.ShoppingCart.entity;

import javax.persistence.*;

@Entity
@Table(name = "order_products")
public class OrderProduct {
    @Id
    @Column(name = "id")
    private int id;
    @OneToOne
    @JoinColumn(name = "order_id")
    private Order orderId;
    @OneToOne
    @JoinColumn(name = "product_id")
    private Product productId;


}
