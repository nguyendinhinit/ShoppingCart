package com.rookies.nashtech.ShoppingCart.service.impl;

import com.rookies.nashtech.ShoppingCart.dto.ProductDTO;
import com.rookies.nashtech.ShoppingCart.entity.Product;
import com.rookies.nashtech.ShoppingCart.mapper.ProductsMapper;
import com.rookies.nashtech.ShoppingCart.repository.ProductRepository;
import com.rookies.nashtech.ShoppingCart.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductsMapper productsMapper;
    private final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, ProductsMapper productsMapper) {
        this.productRepository = productRepository;
        this.productsMapper = productsMapper;
    }

    //    no agr constructor for unit test
    public ProductServiceImpl() {

    }

    @Override
    public ProductDTO findProductByID(Integer id) {
        logger.info("Find Product by Id with id:" + id);
        Product product = productRepository.findProductById(id);
        return productsMapper.fromEntity(product);
    }

    @Override
    public List<ProductDTO> filterProduct(String keyword, Double price) {
        List<Product> products = productRepository.filter(keyword, price);
        return products.stream().map(productsMapper::fromEntity).collect(Collectors.toList());
    }

    @Override
    public List<ProductDTO> findProductByPriceWithPaging(Double price, Integer paging) {
        Pageable firstPageWithPagingElement = PageRequest.of(0, paging);
        Pageable secondPageWithPagingElement = PageRequest.of(1, paging);
        Page<Product> allProduct = productRepository.findProductsByPrice(price,firstPageWithPagingElement);
        return allProduct.stream().map(productsMapper::fromEntity).collect(Collectors.toList());
    }
}
