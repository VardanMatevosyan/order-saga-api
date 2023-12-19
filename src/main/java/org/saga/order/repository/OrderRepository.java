package org.saga.order.repository;

import org.saga.order.dto.saga.order.OrderStatus;
import org.saga.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface OrderRepository extends JpaRepository<Order, Long> {

  // investigate this later enum type
//  @Modifying
//  @Query("update Order o set o.orderStatus =:orderStatus where o.id =:orderId")
//  void updateOrderStatus(Long orderId, String orderStatus);
}
