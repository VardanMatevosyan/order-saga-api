package org.saga.order.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.saga.order.dto.saga.inventory.event.InventoryOrderEvent;
import org.saga.order.dto.saga.notification.NotifyEvent;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface NotificationEventMapper {

  @Mapping(target = "customerEmail", source = "inventoryOrderEvent.inventoryDto.customerEmail")
  @Mapping(target = "orderId", source = "inventoryOrderEvent.inventoryDto.orderId")
  @Mapping(target = "productItems", source = "inventoryOrderEvent.inventoryDto.productItems")
  @Mapping(target = "orderStatus", source = "orderStatus")
  @Mapping(target = "price", source = "inventoryOrderEvent.inventoryDto.orderPrice")
  NotifyEvent toEvent(InventoryOrderEvent inventoryOrderEvent, String orderStatus);


}
