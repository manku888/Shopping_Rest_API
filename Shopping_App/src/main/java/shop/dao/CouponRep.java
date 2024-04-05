package shop.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import shop.entity.Coupon;

@Repository
public interface CouponRep extends JpaRepository<Coupon, Long> {
    Coupon findByCode(String code);
    List<Coupon> findByUsedByUserId(Long userId);
}
