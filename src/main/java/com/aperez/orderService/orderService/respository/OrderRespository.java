package com.aperez.orderService.orderService.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aperez.orderService.orderService.domain.Order;

public interface OrderRespository extends JpaRepository<Order, Long> {
	List<Order> findByCustomerId(String customerId);
}
