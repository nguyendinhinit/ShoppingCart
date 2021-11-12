package com.rookies.nashtech.ShoppingCart.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "orders")
public class Order {
  @Id
  @Column(name = "id", nullable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @ManyToOne
  @JoinColumn(name = "username")
  private User user;

  @Column(name = "date_created")
  private Date dateCreated;

  @Column(name = "state")
  private String state;

  @Column(name = "total_cost")
  private float totalCost;
}
