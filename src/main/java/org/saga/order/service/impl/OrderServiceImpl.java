package org.saga.order.service.impl;

import static org.saga.common.enums.OrderStatus.ORDER_CREATED;

import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.saga.common.dto.order.OrderCreateRequest;
import org.saga.common.dto.order.event.OrderCreateEvent;
import org.saga.common.enums.OrderStatus;
import org.saga.order.entity.Order;
import org.saga.order.mapper.OrderCreateRequestMapper;
import org.saga.order.repository.OrderRepository;
import org.saga.order.service.OrderService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderServiceImpl implements OrderService {

  final OrderRepository orderRepository;
  final OrderCreateRequestMapper mapper;
  final MessageBroker messageBroker;

  @Value("${order-saga-topic}")
  String orderTopicName;

  @Override
  @Transactional
  public void create(OrderCreateRequest orderCreateRequest) {
    log.info("Create perches order request %s".formatted(orderCreateRequest));
    Order order = createOrder(orderCreateRequest);
    sendOrderCreateEvent(orderCreateRequest, order);
    log.info("perches order request processed");
  }

  @Override
  @Transactional
  public void updateOrderStatus(Long orderId, String orderStatus) {
    log.info("Updating order status to %s with id %s ".formatted(orderStatus, orderId));
    Order order = orderRepository.findById(orderId).orElseThrow();
    order.setOrderStatus(OrderStatus.valueOf(orderStatus));
  }

  private void sendOrderCreateEvent(OrderCreateRequest orderCreateRequest, Order order) {
    orderCreateRequest.setOrderId(order.getId());
    OrderCreateEvent orderCreateEvent = mapper.toEvent(orderCreateRequest, ORDER_CREATED);
    messageBroker.send(orderTopicName, orderCreateEvent);
  }

  private Order createOrder(OrderCreateRequest orderCreateRequest) {
    Order order = mapper.toEntity(orderCreateRequest);
    return orderRepository.save(order);
  }
}
