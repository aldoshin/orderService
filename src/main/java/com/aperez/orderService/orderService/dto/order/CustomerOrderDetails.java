package com.aperez.orderService.orderService.dto.order;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.aperez.orderService.orderService.dto.customer.Customer;
import com.aperez.orderService.orderService.dto.product.Item;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class CustomerOrderDetails {
	private Long orderId;
    private String externalReference;
    private Customer customer;
    private LocalDateTime createdDate;
    private List<Item> items;
    private BigDecimal totalOrderCost;
    private BigDecimal totalOrderTax;
}
