package org.saga.order.service;

import org.saga.common.dto.order.OrderCreateRequest;

public interface OrderService {

  void create(OrderCreateRequest orderCreateRequest);

  void updateOrderStatus(Long orderId, String orderStatus);
}
