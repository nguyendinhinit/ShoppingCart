package com.rookies.nashtech.ShoppingCart.dto;

import lombok.Data;

@Data
public class ProductDTO {
    private int id;
    private String name;
    private float price;
    private String brand;
    private String color;
    private String category;
    private int quantity;
}
