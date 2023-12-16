package org.saga.order.rest;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.saga.order.dto.saga.order.OrderCreateRequest;
import org.saga.order.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Slf4j
public class OrderController {

  OrderService OrderService;

  @PostMapping(path = "/order", consumes = APPLICATION_JSON_VALUE)
  public ResponseEntity<Void> createOrder(@RequestBody OrderCreateRequest orderCreateRequest) {
    OrderService.create(orderCreateRequest);
    log.info("Order created %s".formatted(orderCreateRequest));
    return ResponseEntity.ok().build();
  }

}
