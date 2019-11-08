package com.aperez.orderService.orderService.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aperez.orderService.orderService.domain.Item;
import com.aperez.orderService.orderService.domain.Order;
import com.aperez.orderService.orderService.dto.order.CustomerOrderDetails;
import com.aperez.orderService.orderService.dto.request.CustomerOrderRequest;
import com.aperez.orderService.orderService.respository.OrderRespository;
import com.aperez.orderService.orderService.service.ProductServiceProxy;

@RestController
public class OrderController {
	
	private OrderRespository orderRepository;
    private ProductServiceProxy productServiceProxy;

    @Autowired
    public OrderController(OrderRespository orderRepository, ProductServiceProxy productServiceProxy) {
        this.orderRepository = orderRepository;
        this.productServiceProxy = productServiceProxy;
    }
	
    @PostMapping("/orders")
	public Order save(@RequestBody CustomerOrderRequest request) {
		return orderRepository.save(Order
                .builder()
                .customerId(request.getCustomerId())
                .externalReference(request.getExternalReference())
                .items(toItems(request.getItems()))
                .build());
	}
    
    @GetMapping("/orders")
    public List<CustomerOrderDetails> getCustomerOrders(@RequestParam String customerId) {
    	final List<Order> orders = orderRepository.findByCustomerId(customerId);
    	return orders.stream().map(this::toCustomerOrderDetails).collect(Collectors.toList());
    }
    
    @GetMapping("/orders/{id}")
    public CustomerOrderDetails getOrders(@PathVariable("id") Long orderId) {
    	final Order order = orderRepository.findById(orderId).orElse(null);
    	if(order == null) {
    		return null;
    	}
    	return toCustomerOrderDetails(order);
    }
	
	public CustomerOrderDetails toCustomerOrderDetails(Order order) {
		return CustomerOrderDetails.builder()
				.orderId(order.getId())
				.createdDate(order.getCreatedDate())
				.externalReference(order.getExternalReference())
                .items(toItemList(order.getItems()))
                .build();
	}
	
	private List<com.aperez.orderService.orderService.dto.product.Item> toItemList(List<Item> items) {
		return items.stream().map(item -> toItemDto(item)).collect(Collectors.toList());
	}
	
	private com.aperez.orderService.orderService.dto.product.Item toItemDto(Item item) {
		return com.aperez.orderService.orderService.dto.product.Item
				.builder()
				.product(productServiceProxy.getProduct(item.getProductId()))
				.quantity(item.getQuantity())
				.build();
	}
	
	private List<Item> toItems(List<com.aperez.orderService.orderService.dto.request.Item> items) {
		return items.stream()
				.map(item -> Item
						.builder()
						.productId(item.getProductId())
						.quantity(item.getQuantity())
						.build())
				.collect(Collectors.toList());
    }

}
