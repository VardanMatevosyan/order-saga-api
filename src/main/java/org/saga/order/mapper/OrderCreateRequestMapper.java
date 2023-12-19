package org.saga.order.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.saga.order.dto.saga.order.OrderCreateRequest;
import org.saga.order.dto.saga.order.OrderStatus;
import org.saga.order.dto.saga.order.event.OrderCreateEvent;
import org.saga.order.entity.Order;

@Mapper(componentModel = "spring",
    unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrderCreateRequestMapper {

  @Mapping(target = "customerEmail", source = "customerEmail")
  @Mapping(target = "price", source = "price")
  @Mapping(target = "orderStatus", constant = "ORDER_CREATED")
  Order toEntity(OrderCreateRequest orderCreateRequest);

  OrderCreateEvent toEvent(OrderCreateRequest orderCreateRequest, OrderStatus orderStatus);

}
