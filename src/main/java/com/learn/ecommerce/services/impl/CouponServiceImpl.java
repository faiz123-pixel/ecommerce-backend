package com.learn.ecommerce.services.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.learn.ecommerce.controllers.OrdersController;
import com.learn.ecommerce.dtos.CouponsDto;
import com.learn.ecommerce.entities.Coupons;
import com.learn.ecommerce.enumes.CouponDiscountType;
import com.learn.ecommerce.repositories.CouponRepository;
import com.learn.ecommerce.services.CouponService;

@Service
public class CouponServiceImpl implements CouponService{

    private final OrdersController ordersController;
	
	@Autowired 
	private CouponRepository couponRepository;
	
	@Autowired
	private ModelMapper modelMapper;

    CouponServiceImpl(OrdersController ordersController) {
        this.ordersController = ordersController;
    }

	@Override
	public CouponsDto addCoupon(CouponsDto couponsDto) {
		Coupons coupons = modelMapper.map(couponsDto, Coupons.class);
		if(CouponDiscountType.FIXEDAMOUNT.toString()==couponsDto.getCouponDiscountType().toString().toUpperCase())
		{
			coupons.setCouponDiscountType(CouponDiscountType.FIXEDAMOUNT);
		}
		if(CouponDiscountType.PERCENTAGE.toString()==couponsDto.getCouponDiscountType().toString().toUpperCase())
		{
			coupons.setCouponDiscountType(CouponDiscountType.PERCENTAGE);
		}
		coupons.setStatus(true);
		
		return modelMapper.map(couponRepository.save(coupons), CouponsDto.class);
	}

	@Override
	public CouponsDto updateCoupon(Integer id, CouponsDto couponsDto) {
		Coupons coupons = couponRepository.findById(id).orElseThrow(()->new RuntimeException("Coupon not found"));
		coupons.setStatus(couponsDto.isStatus());
		coupons.setCouponCode(couponsDto.getCouponCode());
		coupons.setCouponDiscountType(couponsDto.getCouponDiscountType());
		coupons.setDiscountValue(couponsDto.getDiscountValue());
		coupons.setUsageLimit(couponsDto.getUsageLimit());
		coupons.setValidFrom(couponsDto.getValidFrom());
		coupons.setValidTo(couponsDto.getValidTo());
		return modelMapper.map(couponRepository.save(coupons), CouponsDto.class);
	}

	@Override
	public void deleteCoupon(Integer id) {
		Coupons coupons = couponRepository.findById(id).orElseThrow(()->new RuntimeException("Coupon not found"));
		couponRepository.delete(coupons);
		
	}

	@Override
	public List<CouponsDto> getAllCoupons() {
		List<Coupons> coupons = couponRepository.findAll();
		return coupons.stream().map((c)->modelMapper.map(c, CouponsDto.class)).toList();
	}

	@Override
	public CouponsDto getCouponById(Integer id) {
		Coupons coupons = couponRepository.findById(id).orElseThrow(()->new RuntimeException("Coupon not found"));
		return modelMapper.map(coupons, CouponsDto.class);
	}

	@Override
	public CouponsDto getCouponByCode(String code) {
		Coupons coupons = couponRepository.findByCouponCode(code) ;
		return modelMapper.map(coupons, CouponsDto.class);
	}

	@Override
	public CouponsDto activateCoupon(Integer id) {
		Coupons coupons = couponRepository.findById(id).orElseThrow(()->new RuntimeException("Coupon not found"));
		coupons.setStatus(true);
		return modelMapper.map(couponRepository.save(coupons), CouponsDto.class);
	}

	@Override
	public CouponsDto deactivateCoupon(Integer id) {
		Coupons coupons = couponRepository.findById(id).orElseThrow(()->new RuntimeException("Coupon not found"));
		coupons.setStatus(false);
		return modelMapper.map(couponRepository.save(coupons), CouponsDto.class);
	}
	
	@Override
	public void reduceUsage(Integer id){

		Coupons coupons = couponRepository.findById(id).orElseThrow(()->new RuntimeException("Coupon not found"));

	    if(coupons.getUsageLimit() > 0){

	        coupons.setUsageLimit(
	            coupons.getUsageLimit() - 1
	        );

	        couponRepository.save(coupons);
	    }
	}

}
