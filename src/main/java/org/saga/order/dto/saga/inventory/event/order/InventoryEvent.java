package org.saga.order.dto.saga.inventory.event.order;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import org.saga.order.dto.saga.EventDto;
import org.saga.order.dto.saga.inventory.InventoryDto;
import org.saga.order.dto.saga.payment.event.PaymentStatus;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class InventoryEvent extends EventDto {

  InventoryDto inventoryDto;
  PaymentStatus paymentStatus;

}
