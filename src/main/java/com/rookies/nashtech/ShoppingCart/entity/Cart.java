package com.rookies.nashtech.ShoppingCart.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "carts")
public class Cart {
    @Id
    @Column(name="id")
    private int id;

    @Column(name="phone")
    private String phone;
}
