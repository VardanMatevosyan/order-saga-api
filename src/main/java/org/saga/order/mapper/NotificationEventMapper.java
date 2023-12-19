package org.saga.order.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.saga.order.dto.saga.inventory.event.order.InventoryEvent;
import org.saga.order.dto.saga.notification.NotifyEvent;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface NotificationEventMapper {

  @Mapping(target = "customerEmail", source = "inventoryEvent.inventoryDto.customerEmail")
  @Mapping(target = "orderId", source = "inventoryEvent.inventoryDto.orderId")
  @Mapping(target = "productItems", source = "inventoryEvent.inventoryDto.productItems")
  @Mapping(target = "orderStatus", source = "orderStatus")
  @Mapping(target = "price", source = "inventoryEvent.inventoryDto.orderPrice")
  NotifyEvent toEvent(InventoryEvent inventoryEvent, String orderStatus);


}
