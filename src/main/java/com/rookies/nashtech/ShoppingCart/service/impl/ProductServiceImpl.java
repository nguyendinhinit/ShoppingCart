package com.rookies.nashtech.ShoppingCart.service.impl;

import com.rookies.nashtech.ShoppingCart.dto.ProductDTO;
import com.rookies.nashtech.ShoppingCart.entity.Product;
import com.rookies.nashtech.ShoppingCart.mapper.ProductMapper;
import com.rookies.nashtech.ShoppingCart.repository.ProductRepository;
import com.rookies.nashtech.ShoppingCart.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
  private final ProductRepository productRepository;
  private final ProductMapper productsMapper;
  private final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

  @Autowired
  public ProductServiceImpl(ProductRepository productRepository, ProductMapper productsMapper) {
    this.productRepository = productRepository;
    this.productsMapper = productsMapper;
  }

  @Override
  public ProductDTO findProductByID(Integer id) {
    logger.info("Find Product by Id with id:" + id);
    Product product = productRepository.findProductById(id);
    return productsMapper.fromEntity(product);
  }

  @Override
  public List<ProductDTO> filterProduct(String keyword, Float price) {
    List<Product> products = productRepository.filter(keyword, price);
    return products.stream().map(productsMapper::fromEntity).collect(Collectors.toList());
  }

  /**
   * Decrease Product quantity in case update Cart.
   * 
   * @param id The Product id
   * @param number The decrease number
   * @return The ProductDTO has been decreased.
   * @author ManhTuan
   */
  @Override
  @Transactional
  public ProductDTO decreaseProductQuantity(Integer id, Integer number) {
    Product product = findProductEntityByID(id);
    int currentQuantity = product.getQuantity();
    if (currentQuantity < number) {
      logger.error("Quantity to be reduced is more than existing quantity.");
      throw new IllegalArgumentException("Quantity to be reduced is more than existing quantity.");
    }
    logger.info("Decrease Product quantity by " + number + " unit(s).");
    product.setQuantity(currentQuantity - number);
    Product decreasedQuantityProduct = productRepository.save(product);
    logger.info("Product quantity has been decreased " + number + " unit(s).");
    return productsMapper.fromEntity(decreasedQuantityProduct);
  }

  /**
   * Increase Product quantity in case update Cart.
   * 
   * @param id The Product id
   * @param number The increase number
   * @return The ProductDTO has been increased.
   * @author ManhTuan
   */
  @Override
  @Transactional
  public ProductDTO increaseProductQuantity(Integer id, Integer number) {
    Product product = findProductEntityByID(id);
    int currentQuantity = product.getQuantity();
    logger.info("Increase Product quantity by " + number + " unit(s).");
    product.setQuantity(currentQuantity + number);
    Product decreasedQuantityProduct = productRepository.save(product);
    logger.info("Product quantity has been increased " + number + " unit(s).");
    return productsMapper.fromEntity(decreasedQuantityProduct);
  }

  /**
   * Get Product Entity by id
   * 
   * @param id The Integer id input
   * @return A Product Entity
   * @author ManhTuan
   */
  @Override
  public Product findProductEntityByID(Integer id) {
    logger.info("Find Product by Id with id:" + id);
    Product product = productRepository.findProductById(id);
    if (product == null) {
      logger.error("Product not found with id :" + id);
      throw new IllegalArgumentException("Product not found with id :" + id);
    }
    return product;
  }
}
