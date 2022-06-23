package app.core.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import app.core.entities.Coupon;
import app.core.exceptions.CouponSystemException;
import app.core.repasitories.CouponRepository;

@Service
@Transactional(rollbackFor = CouponSystemException.class)

public class JobService {
@Autowired
private	CouponRepository couponRepo;

public void deleteAllExpieredCoupons() {
	
List<Coupon> expiredCoupon = couponRepo.findByEndDateLessThanEqual(LocalDate.now());

couponRepo.deleteAllInBatch(expiredCoupon);
	
}
	
}