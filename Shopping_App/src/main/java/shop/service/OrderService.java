package shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shop.dao.OrderRep;
import shop.entity.Order;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    private OrderRep orderRep;

    public Order createOrder(Order order) {
        return orderRep.save(order);
    }

    public List<Order> getAllOrders() {
        return orderRep.findAll();
    }

    public Optional<Order> getOrderById(Long id) {
        return orderRep.findById(id);
    }

    public void updateOrder(Long id, Order updatedOrder) {
//        Optional<Order> optionalOrder = orderRep.findById(id);
//        if (optionalOrder.isPresent()) {
//            updatedOrder.setId(id);
//            return orderRep.save(updatedOrder);
//        } else {
//            throw new IllegalArgumentException("Order not found with id: " + id);
//        }
    	updatedOrder.setId(id);
        this.orderRep.save(updatedOrder);
    }

    public void deleteOrder(Long id) {
        orderRep.deleteById(id);
    }
}
