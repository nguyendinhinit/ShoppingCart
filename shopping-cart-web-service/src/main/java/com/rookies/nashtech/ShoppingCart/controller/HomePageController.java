package com.rookies.nashtech.ShoppingCart.controller;

import com.rookies.nashtech.ShoppingCart.dto.ProductDTO;
import com.rookies.nashtech.ShoppingCart.service.ProductService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class HomePageController {
    private final ProductService productService;

    @Autowired
    public HomePageController(ProductService productService){
        this.productService = productService;
    }

    @ApiOperation(value = "Get An Asset Using id", response = ProductDTO.class)
    @GetMapping(value = "/assets/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductDTO> getProduct(@PathVariable Integer id) {
//        logger.info("Execute getAsset() inside AssetController");
        ProductDTO product = productService.findProductByID(id);
//        logger.info("Executed successful!");
        return ResponseEntity.ok(product);
    }
}
