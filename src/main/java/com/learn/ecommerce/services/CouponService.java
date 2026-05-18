package com.learn.ecommerce.services;

import java.util.List;

import com.learn.ecommerce.dtos.CouponsDto;

public interface CouponService {
	
	CouponsDto addCoupon(CouponsDto couponsDto);
	CouponsDto updateCoupon(Integer id,CouponsDto couponsDto);
	void deleteCoupon(Integer id);
	List<CouponsDto> getAllCoupons();
	CouponsDto getCouponById(Integer id);
	CouponsDto getCouponByCode(String code);
	CouponsDto activateCoupon(Integer id);
	CouponsDto deactivateCoupon(Integer id);
	public void reduceUsage(Integer id);
	

}
