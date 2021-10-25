package com.rookies.nashtech.ShoppingCart.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "cart_item")
public class CartItem {
    @Id
    @Column(name = "id")
    private int id;
    @OneToOne
    @JoinColumn(name = "carts_id")
    private Cart cartId;
    @OneToOne
    @JoinColumn(name = "product_id")
    private Product productId;

    @Column(name = "quantity")
    private int quantity;
}
