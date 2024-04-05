package shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import shop.dao.CouponRep;
import shop.entity.Coupon;

@Service
public class CouponService {

    @Autowired
    private CouponRep couponRepository;

    public Coupon applyCoupon(String code, Long userId) throws Exception {
        // Retrieve the coupon from the repository by code
        Coupon coupon = couponRepository.findByCode(code);

        // Check if the coupon exists and is valid
        if (coupon != null && coupon.isValid()) {
            // Check if the coupon has not been used before by the user
            if (!coupon.isAlreadyUsedByUser(userId)) {
                // Apply the coupon for the user
                coupon.setUsedByUserId(userId);
                couponRepository.save(coupon); // Update the coupon usage status
                return coupon; // Return the applied coupon
            } else {
                // Coupon has already been used by the user
                throw new Exception("Coupon has already been used by this user.");
            }
        } else {
            // Coupon is either invalid or does not exist
            throw new Exception("Invalid coupon code.");
        }
    }

    public List<Coupon> getAllCoupons() {
        // Retrieve all coupons from the repository
        return couponRepository.findAll();
    }

    public List<Coupon> getCouponsByUser(Long userId) {
        // Retrieve all coupons used by a specific user
        return couponRepository.findByUsedByUserId(userId);
    }

    public void deleteCoupon(String code) throws Exception {
        // Delete a coupon by its code
        Coupon coupon = couponRepository.findByCode(code);
        if (coupon != null) {
            couponRepository.delete(coupon);
        } else {
            throw new Exception("Coupon not found for code: " + code);
        }
    }
}
