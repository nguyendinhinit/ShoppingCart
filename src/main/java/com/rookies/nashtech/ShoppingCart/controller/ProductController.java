package com.rookies.nashtech.ShoppingCart.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.rookies.nashtech.ShoppingCart.dto.ProductDTO;
import com.rookies.nashtech.ShoppingCart.service.ProductService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;

@RestController
@RequestMapping("/api/v1")
public class ProductController {
  private final ProductService productService;

  @Autowired
  public ProductController(ProductService productService) {
    this.productService = productService;
  }

  @ApiOperation(value = "Get a product using id", authorizations = {@Authorization(value = "jwtToken")}, response = ProductDTO.class)
  @GetMapping(value = "/product/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<ProductDTO> getProduct(@PathVariable Integer id) {
    ProductDTO product = productService.findProductByID(id);
    return ResponseEntity.ok(product);
  }

  @GetMapping(value = "/product/filter")
  public ResponseEntity<List<ProductDTO>> filter(@RequestParam(name = "keyword", required = false) String keyword, @RequestParam(name = "price", required = false) Double price) {
    List<ProductDTO> products = productService.filterProduct(keyword, price);
    return ResponseEntity.ok(products);
  }
  // TODO: pageable + Spring data JPA specification
  // SOLID

  @ApiOperation(value = "Get all product", authorizations = {@Authorization(value = "jwtToken")})
  @GetMapping(value = "/product/getall")
  public ResponseEntity<List<ProductDTO>> getAll() {
    List<ProductDTO> products = productService.findProductByPriceWithPaging(100.0, 5);
    return ResponseEntity.ok(products);
  }
}
