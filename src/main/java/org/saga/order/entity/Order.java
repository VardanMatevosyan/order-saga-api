package org.saga.order.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.ColumnDefault;
import org.saga.common.enums.InventoryStatus;
import org.saga.common.enums.OrderStatus;
import org.saga.common.enums.PaymentStatus;

@Entity
@Table(name = "orders", schema = "perches")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Order {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;

  @Column(name = "customer_email")
  String customerEmail;

  @Column(name = "price")
  Integer price;

  @Enumerated(value = EnumType.STRING)
  @Column(name = "order_status")
  @ColumnDefault(value = "ORDER_CREATED")
  OrderStatus orderStatus;

  @Enumerated(value = EnumType.STRING)
  @Column(name = "inventory_status")
  InventoryStatus inventoryStatus;

  @Enumerated(value = EnumType.STRING)
  @Column(name = "payment_status")
  PaymentStatus paymentStatus;

}
