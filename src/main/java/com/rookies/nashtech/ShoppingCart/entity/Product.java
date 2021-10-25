package com.rookies.nashtech.ShoppingCart.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "products")
public class Product {
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "price")
    private float price;
    @Column(name = "brach")
    private String branch;
    @Column(name = "color")
    private String color;
    @OneToOne
    @JoinColumn(name = "category_id")
    private Category categoryId;
    @Column(name = "quantity")
    private int quantity;
}
