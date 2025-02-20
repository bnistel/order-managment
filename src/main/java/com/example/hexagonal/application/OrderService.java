package com.example.hexagonal.application;

import com.example.hexagonal.domain.model.Order;
import com.example.hexagonal.domain.model.OrderItem;
import com.example.hexagonal.adapter.out.OrderRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class OrderService {

    @Inject
    OrderRepository orderRepository;

    @Transactional
    public Order createOrder(Order order) {
        orderRepository.persist(order);
        return order;
    }

    @Transactional
    public void addItemToOrder(Long orderId, OrderItem item) {
        Order order = orderRepository.findById(orderId);
        if(order !=null) {
            order.addItem(item);
            orderRepository.persist(order);
        }
    }

    @Transactional
    public void updateOrderTatus(Long orderId, String status) {
        Order order = orderRepository.findById(orderId);
        if(order !=null) {
            order.uodateStatus(status);
            orderRepository.persist(order);
        }
    }

    public List<Order> getAllOrders() {
        return orderRepository.listAll();
    }

    public Order findOrderById(Long id) {
        return orderRepository.findById(id);
    }
}
