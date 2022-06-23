package app.core.repasitories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import app.core.entities.Coupon;
import app.core.entities.Coupon.Category;

public interface CouponRepository extends JpaRepository<Coupon, Integer>{
	List<Coupon> findByCompanyIdAndPriceLessThanEqual(int companyId,double maxPrice);
	List<Coupon> findByCategoryAndCompanyId(Category category,int companyId);
	List<Coupon> findByCategory(Category category);
	Boolean existsByDescriptionAndCompanyId(String discreption,int companyId);
	
	List<Coupon> findByEndDateLessThanEqual(LocalDate date);
	List<Coupon> findByCustomersId(int customerId);
	List<Coupon> findAllByCompanyId(int companyId);
	List<Coupon> findByCategoryAndCustomersId(Category category,int customerId);
	List<Coupon> findByCustomersIdAndPriceLessThanEqual(int customerId,double maxPrice);
	List<Coupon> findByPriceLessThanEqual(double maxPrice);
}