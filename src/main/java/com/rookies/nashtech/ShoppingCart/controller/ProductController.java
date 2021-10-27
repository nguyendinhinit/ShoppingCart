package com.rookies.nashtech.ShoppingCart.controller;

import com.rookies.nashtech.ShoppingCart.dto.ProductDTO;
import com.rookies.nashtech.ShoppingCart.service.ProductService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @ApiOperation(value = "Get An Asset Using id", response = ProductDTO.class)
    @GetMapping(value = "/product/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductDTO> getProduct(@PathVariable Integer id) {
        ProductDTO product = productService.findProductByID(id);
        return ResponseEntity.ok(product);
    }

    @GetMapping(value = "/product/filter")
    public ResponseEntity<List<ProductDTO>> filter(@RequestParam(name = "keyword", required = false) String keyword,
                                             @RequestParam(name = "price",required = false) Float price) {
        List<ProductDTO> products = productService.filterProduct(keyword,price);
        return ResponseEntity.ok(products);
    }

}
