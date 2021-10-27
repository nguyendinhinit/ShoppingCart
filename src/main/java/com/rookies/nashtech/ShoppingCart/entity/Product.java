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
    @Column(name = "brand")
    private String brand;
    @Column(name = "color")
    private String color;
    @OneToOne
    @JoinColumn(name = "category_id")
    private Category category;
    @Column(name = "quantity")
    private int quantity;

    //    Constructor to testing
    public Product(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
