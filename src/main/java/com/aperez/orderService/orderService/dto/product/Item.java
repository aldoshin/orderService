package com.aperez.orderService.orderService.dto.product;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class Item {
	private Product product;
    private BigDecimal quantity;
}
