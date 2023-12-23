package org.saga.order.listener;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.saga.order.dto.saga.inventory.event.InventoryOrderEvent;
import org.saga.order.dto.saga.notification.NotifyEvent;
import org.saga.order.dto.saga.order.OrderStatus;
import org.saga.order.dto.saga.payment.event.PaymentStatus;
import org.saga.order.mapper.NotificationEventMapper;
import org.saga.order.service.OrderService;
import org.saga.order.service.impl.MessageBroker;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
public class InventorySagaListener {

  private final MessageBroker messageBroker;
  private final OrderService orderService;
  private final NotificationEventMapper notificationEventMapper;

  @Value("${notification-saga-topic}")
  String notificationTopicName;

  @KafkaListener(groupId = "order-consumer", topics = {"inventory-order-saga-topic"},
  properties = {"spring.json.value.default.type=org.saga.order.dto.saga.inventory.event.InventoryOrderEvent"})
  public void listener(InventoryOrderEvent inventoryOrderEvent) {
    log.info("Received inventory event %s".formatted(inventoryOrderEvent));

    PaymentStatus paymentStatus = inventoryOrderEvent.getPaymentStatus();
    Long orderId = inventoryOrderEvent.getInventoryDto().getOrderId();

    String orderStatus = OrderStatus.getOrderStatus(paymentStatus);
    orderService.updateOrderStatus(orderId, orderStatus);

    NotifyEvent notifyEvent = notificationEventMapper.toEvent(inventoryOrderEvent, orderStatus);
    messageBroker.send(notificationTopicName, notifyEvent);
  }

}
