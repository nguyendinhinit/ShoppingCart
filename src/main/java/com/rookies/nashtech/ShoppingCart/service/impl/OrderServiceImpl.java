package com.rookies.nashtech.ShoppingCart.service.impl;

import com.rookies.nashtech.ShoppingCart.common.BaseConstants;
import com.rookies.nashtech.ShoppingCart.dto.OrderDTO;
import com.rookies.nashtech.ShoppingCart.dto.OrderProductDTO;
import com.rookies.nashtech.ShoppingCart.entity.Order;
import com.rookies.nashtech.ShoppingCart.entity.OrderProduct;
import com.rookies.nashtech.ShoppingCart.entity.Product;
import com.rookies.nashtech.ShoppingCart.entity.User;
import com.rookies.nashtech.ShoppingCart.exception.InvalidDataFormatException;
import com.rookies.nashtech.ShoppingCart.exception.OrderNotFoundException;
import com.rookies.nashtech.ShoppingCart.exception.ProductNotFoundException;
import com.rookies.nashtech.ShoppingCart.exception.UserNotFoundException;
import com.rookies.nashtech.ShoppingCart.mapper.OrderMapper;
import com.rookies.nashtech.ShoppingCart.mapper.OrderProductMapper;
import com.rookies.nashtech.ShoppingCart.payload.OrderPayload;
import com.rookies.nashtech.ShoppingCart.payload.ProductPayload;
import com.rookies.nashtech.ShoppingCart.repository.OrderProductRepository;
import com.rookies.nashtech.ShoppingCart.repository.OrderRepository;
import com.rookies.nashtech.ShoppingCart.repository.ProductRepository;
import com.rookies.nashtech.ShoppingCart.repository.UserRepository;
import com.rookies.nashtech.ShoppingCart.service.OrderService;
import com.rookies.nashtech.ShoppingCart.util.Calculator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
  private final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);
  private final OrderRepository orderRepository;
  private final ProductRepository productRepository;
  private final UserRepository userRepository;
  private final OrderProductRepository orderProductRepository;
  private final OrderMapper orderMapper;
  private final OrderProductMapper orderProductMapper;

  public OrderServiceImpl(OrderRepository orderRepository, ProductRepository productRepository, UserRepository userRepository, OrderProductRepository orderProductRepository, OrderMapper orderMapper, OrderProductMapper orderProductMapper) {
    this.orderRepository = orderRepository;
    this.productRepository = productRepository;
    this.userRepository = userRepository;
    this.orderProductRepository = orderProductRepository;
    this.orderMapper = orderMapper;
    this.orderProductMapper = orderProductMapper;
  }

  /**
   * Get all Orders
   *
   * @return The Order list
   */
  @Transactional
  @Override
  public List<OrderDTO> getAllOrders() {
    return orderRepository.findAll().stream().map(orderMapper::fromEntity).collect(Collectors.toList());
  }

  /**
   * Get an Order by Order ID
   *
   * @param orderId Order ID
   * @return Order with given ID
   */
  @Transactional
  @Override
  public OrderDTO getOrderById(Integer orderId) {
    logger.info("Get Order by ID: START");
    Order order = orderRepository.findOrderById(orderId);
    if (Optional.ofNullable(order).isEmpty()) {
      logger.error("Get Order by ID: ERROR - No order of id {}", orderId);
      throw new OrderNotFoundException("No order of id " + orderId);
    }
    return orderMapper.fromEntity(order);
  }

  /**
   * Pay an Order by Order ID
   *
   * @param orderId Order ID
   * @return Order with state 'paid'
   */
  @Transactional
  @Override
  public OrderDTO payOrderById(Integer orderId) {
    logger.info("Pay an Order By ID: START");
    Order currentOrder = orderRepository.findOrderById(orderId);
    if (currentOrder == null) {
      logger.error("Pay an Order By ID: ERROR - No order of id {}", orderId);
      throw new OrderNotFoundException("No order of id " + orderId);
    }
    currentOrder.setState(BaseConstants.ORDER_PAID);
    orderRepository.save(currentOrder);
    logger.info("Pay an Order By ID: DONE");
    return orderMapper.fromEntity(currentOrder);
  }

  /**
   * Create an Order
   *
   * @param payload Payload that contains order info and product info (id, quantity)
   * @return Order with state 'paid'
   */
  @Transactional
  @Override
  public OrderDTO createOrder(OrderPayload payload) {
    // persist order first
    Optional<User> user = userRepository.findByUsername(payload.getUsername());
    if (user.isEmpty()) {
      throw new UserNotFoundException("No user of username " + payload.getUsername());
    }
    OrderDTO orderDTO = orderMapper.fromPayload(payload);
    Order order = orderMapper.fromDTO(orderDTO);
    order.setUser(user.get());
    orderRepository.save(order);

    // then persist the order-product
    List<ProductPayload> actualPayload = new ArrayList<>();

    List<OrderProductDTO> orderProductList = orderProductMapper.fromPayload(payload);
    for (OrderProductDTO orderProductDTO : orderProductList) {
      OrderProduct orderProduct = new OrderProduct();

      Product product = productRepository.findProductById(orderProductDTO.getProductId());
      if (Optional.ofNullable(product).isEmpty()) {
        throw new ProductNotFoundException("No product of id " + orderProductDTO.getProductId());
      }

      int currentQuantity = product.getQuantity();
      int quantity = orderProductDTO.getQuantity();
      if (quantity > currentQuantity || quantity < 0) {
        throw new InvalidDataFormatException("Invalid quantity value: " + quantity);
      }

      ProductPayload payload1 = new ProductPayload();
      payload1.setPrice(product.getPrice());
      payload1.setQuantity(quantity);
      actualPayload.add(payload1);

      // update product's quantity: newQuantity = currentQuantity - quantity
      product.setQuantity(currentQuantity - quantity);

      orderProduct.setOrder(order);
      orderProduct.setProduct(product);
      orderProduct.setQuantity(quantity);

      productRepository.save(product);
      orderProductRepository.save(orderProduct);
    }

    float totalCost = Calculator.getTotalCost(actualPayload);
    // Get the newly created Order
    List<Order> orders = orderRepository.findAll();
    Order newOrder = orders.get(orders.size() - 1);
    newOrder.setTotalCost(totalCost);
    orderDTO.setId(newOrder.getId());
    orderDTO.setTotalCost(totalCost);

    // persist the order with correct totalCost
    orderRepository.save(newOrder);

    // return the OrderDTO only
    return orderDTO;
  }

  /**
   * Delete an Order by Order ID
   *
   * @param orderId Order ID
   */
  @Transactional
  @Override
  public void deleteOrderById(Integer orderId) {
    Order order = orderRepository.findOrderById(orderId);
    if (Optional.ofNullable(order).isEmpty()) {
      throw new OrderNotFoundException("No order of id " + orderId);
    }

    List<OrderProduct> orderProducts = orderProductRepository.findAllByOrder(order);
    // when order is deleted, all products in that order will be returned to the system
    for (OrderProduct orderProduct : orderProducts) {
      Product product = productRepository.findProductById(orderProduct.getProduct().getId());
      int currentQuantity = product.getQuantity();
      product.setQuantity(currentQuantity + orderProduct.getQuantity());
      productRepository.save(product);
    }

    orderProductRepository.deleteAllByOrder(order);
    orderRepository.deleteById(orderId);
  }
}
