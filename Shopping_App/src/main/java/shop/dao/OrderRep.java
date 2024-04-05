package shop.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import shop.entity.Order;

public interface OrderRep extends JpaRepository<Order, Long> {
//    boolean existsByUserIdAndCoupon_Id(Long userId, long couponId);
}
