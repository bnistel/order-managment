package com.example.hexagonal.adapter.in.web;

import com.example.hexagonal.domain.model.Order;
import com.example.hexagonal.domain.model.OrderItem;
import com.example.hexagonal.application.OrderService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/orders")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class OrderController {

    @Inject
    OrderService orderService;

    @POST
    public Response createOrder(Order order) {
        orderService.createOrder(order);
        return Response.status(Response.Status.CREATED).entity(order).build();
    }

    @POST
    @Path("/{orderId}/status")
    public Response addItemOrder(@PathParam("orderId") long orderId, OrderItem item) {
        orderService.addItemToOrder(orderId, item);
        return Response.ok().build();
    }

    @PUT
    @Path("/{orderId}/status")
    public Response updateOrderStatus(@PathParam("orderId") long orderId, String status) {
        orderService.updateOrderTatus(orderId, status);
        return Response.ok().build();
    }

    @GET
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GET
    @Path("/{orderId}")
    public Order getOrderById(@PathParam("orderId") long orderId) {
        return orderService.findOrderById(orderId);
    }
}
