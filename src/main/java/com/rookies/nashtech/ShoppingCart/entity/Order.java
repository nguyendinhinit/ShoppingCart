package com.rookies.nashtech.ShoppingCart.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
public class Order {
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Column(name = "date_created")
    private Date dateCreated;
    @Column(name = "state")
    private String state;
    @Column(name = "address")
    private String address;
    @OneToOne
    @JoinColumn(name = "phone")
    private Cart phone;
    @Column(name = "quantity")
    private int quantity;
}
