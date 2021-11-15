package com.rookies.nashtech.ShoppingCart.dto;

import lombok.Data;

import java.util.Date;

@Data
public class OrderDTO {
    private int id;
    private String userName;
    private Date dateCreated;
    private float totalCost;
    private String state;
}
