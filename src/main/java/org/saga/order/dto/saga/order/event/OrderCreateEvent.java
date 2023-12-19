package org.saga.order.dto.saga.order.event;

import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import org.saga.order.dto.saga.EventDto;
import org.saga.order.dto.saga.order.OrderCreateRequest;
import org.saga.order.dto.saga.order.OrderStatus;

@Getter
@Setter
@AllArgsConstructor
@Builder
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderCreateEvent extends EventDto {

  OrderCreateRequest orderCreateRequest;
  OrderStatus orderStatus;

  public OrderCreateEvent() {
    super();
  }

}
