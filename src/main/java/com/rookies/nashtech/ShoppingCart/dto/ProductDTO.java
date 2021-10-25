package com.rookies.nashtech.ShoppingCart.dto;

import lombok.Data;

@Data
public class ProductDTO {
    private int id;
    private String name;
    private float price;
    private String brand;
    private String color;
    private int category_id;
    private int quantity;
}
