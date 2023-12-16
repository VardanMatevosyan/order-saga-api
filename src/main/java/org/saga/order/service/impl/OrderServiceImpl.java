package org.saga.order.service.impl;

import org.saga.order.dto.saga.order.OrderCreateRequest;
import org.saga.order.service.OrderService;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

  @Override
  public void create(OrderCreateRequest orderCreateRequest) {
    // create product
  }
}
