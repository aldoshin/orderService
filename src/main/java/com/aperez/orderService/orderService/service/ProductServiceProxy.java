package com.aperez.orderService.orderService.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.aperez.orderService.orderService.dto.product.Product;

@FeignClient(name = "product-service", url = "10.0.0.221:8001")
public interface ProductServiceProxy {

    @GetMapping("/products/{id}")
    Product getProduct(@PathVariable("id") Long id);
}