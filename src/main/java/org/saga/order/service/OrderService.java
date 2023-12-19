package org.saga.order.service;

import org.saga.order.dto.saga.order.OrderCreateRequest;
import org.saga.order.dto.saga.order.OrderStatus;

public interface OrderService {

  void create(OrderCreateRequest orderCreateRequest);

  void updateOrderStatus(Long orderId, String orderStatus);
}
