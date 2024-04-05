package shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shop.entity.Order;
import shop.service.OrderService;

import java.util.List;
import java.util.Optional;

@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/orders")
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        Order createdOrder = orderService.createOrder(order);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdOrder);
    }

    @GetMapping("/orders")
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> orders = orderService.getAllOrders();
        if (orders.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(orders);
        }
    }

    @GetMapping("/orders/{id}")
    public ResponseEntity<Object> getOrderById(@PathVariable("id") Long id) {
        Optional<Order> order = orderService.getOrderById(id);
        if (order.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(order.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order with ID " + id + " is not available");
        }
    }

    @PutMapping("/orders/{id}")
    public ResponseEntity<String> updateOrder(@PathVariable("id") Long id, @RequestBody Order updatedOrder) {
        try {
            this.orderService.updateOrder(id, updatedOrder);
            return ResponseEntity.status(HttpStatus.OK).body("Order is updated successfully. Order ID: " + id );
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unable to update order: " + e.getMessage());
        }
    }

    @DeleteMapping("/orders/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable("id") Long id) {
        try {
            this.orderService.deleteOrder(id);
            return ResponseEntity.status(HttpStatus.OK).body("Order is deleted successfully. Order ID: " + id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unable to delete order: " + e.getMessage());
        }
    }
}