package shop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import shop.entity.Coupon;
import shop.service.CouponService;

@Controller
public class CouponController {

    @Autowired
    private CouponService couponService;

    // Apply coupon
    @PostMapping(value = "/api/coupon", consumes = "application/json")
    public ResponseEntity<Coupon> applyCoupon(@RequestParam String code, @RequestParam Long userId) {
        try {
            Coupon appliedCoupon = couponService.applyCoupon(code, userId);
            return new ResponseEntity<>(appliedCoupon, HttpStatus.OK);
        } catch (Exception e) {
            //return  ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        	return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // Get all coupons
    @GetMapping("/api/coupon")
    public ResponseEntity<List<Coupon>> getAllCoupons() {
        List<Coupon> coupons = couponService.getAllCoupons();
        return new ResponseEntity<>(coupons, HttpStatus.OK);
    }

    // Get coupons by user
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Coupon>> getCouponsByUser(@PathVariable Long userId) {
        List<Coupon> coupons = couponService.getCouponsByUser(userId);
        return new ResponseEntity<>(coupons, HttpStatus.OK);
    }

    // Delete coupon by code
    @DeleteMapping("/{code}")
    public ResponseEntity<?> deleteCoupon(@PathVariable String code) {
        try {
            couponService.deleteCoupon(code);
            return new ResponseEntity<>("Coupon deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
	
	

	
	   
	}


