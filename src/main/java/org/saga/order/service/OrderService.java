package org.saga.order.service;

import org.saga.order.dto.saga.order.OrderCreateRequest;

public interface OrderService {

  void create(OrderCreateRequest orderCreateRequest);

}
