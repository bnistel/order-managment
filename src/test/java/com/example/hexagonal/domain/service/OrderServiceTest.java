package com.example.hexagonal.domain.service;

import com.example.hexagonal.application.OrderService;
import com.example.hexagonal.domain.model.Order;
import com.example.hexagonal.domain.model.OrderItem;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@QuarkusTest
public class OrderServiceTest {

    @Inject
    OrderService orderService;

    @Test
    public void testCreatorOrder() {
        Order order = new Order(LocalDateTime.now(), "PENDING");
        orderService.createOrder(order);
        assertNotNull(order.getId());
    }

    @Test
    public void testAddItemToOrder() {
        Order order = new Order(LocalDateTime.now(), "PENDING");
        orderService.createOrder(order);
        OrderItem item = new OrderItem("product1", 2, new BigDecimal("50.0"));
        orderService.addItemToOrder(order.getId(), item);
        assertEquals(1, orderService.findOrderById(order.getId()).getItems().size());
    }

    @Test void testUpdateOrderStatus() {
        Order order = new Order(LocalDateTime.now(), "PENDING");
        orderService.createOrder(order);
        orderService.updateOrderTatus(order.getId(), "CONFIRMED");
        assertEquals("CONFIRMED", orderService.findOrderById(order.getId()).getStatus());


    }
}
