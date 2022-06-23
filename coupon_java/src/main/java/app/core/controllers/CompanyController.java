package app.core.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import app.core.entities.Company;
import app.core.entities.Coupon;
import app.core.entities.Coupon.Category;
import app.core.exceptions.CouponSystemException;
import app.core.services.CompanyService;
import app.core.services.JwtUtil;

@RestController
@RequestMapping("/api/company")
@CrossOrigin
public class CompanyController {

	private CompanyService compService;
	private JwtUtil jwtUtil;

	public CompanyController(CompanyService compService, JwtUtil jwtUtil) {
		super();
		this.compService = compService;
		this.jwtUtil = jwtUtil;
	}

	@PostMapping("/addCoupon")
	public void addCoupon(@RequestBody Coupon coupon, @RequestHeader String token) {
		try {
			this.compService.addCoupon(coupon, jwtUtil.getId(token));
		} catch (CouponSystemException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	@DeleteMapping("/{couponId}")
	public void deleteCoupon(@PathVariable int couponId, @RequestHeader String token) throws ResponseStatusException {
		try {
			this.compService.deleteCoupon(couponId);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

	@GetMapping("/allCouponsByPrice")
	public List<Coupon> getCouponByMaxPrice(@PathVariable double maxPrice, @RequestHeader String token)
			throws ResponseStatusException {
		try {
			return this.compService.GetAllCouponByMaxPrice(maxPrice, jwtUtil.getId(token));
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

	@GetMapping("/allCoupons")
	public List<Coupon> getAllCoupons(@RequestHeader String token) {
		try {
			return this.compService.GetAllCoupons(jwtUtil.getId(token));
		} catch (CouponSystemException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

	@GetMapping("/companyInfo")
	public Company getCompanyInfo(@RequestHeader String token) throws ResponseStatusException {
		try {
			return this.compService.getCompanyInfo(jwtUtil.getId(token));
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

	@GetMapping("/{couponId}")
	public Coupon getOneCoupon(@PathVariable int couponId, @RequestHeader String token) throws ResponseStatusException {
		try {
			return this.compService.getCoupon(couponId);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

	@GetMapping("/allCouponsByCategory")
	public List<Coupon> getCouponByCategory(@PathVariable Category categoryName, @RequestHeader String token)
			throws ResponseStatusException {
		try {
			return this.compService.GetCouponByCategory(categoryName);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

	@PostMapping("/updateCoupon")
	public void updateCoupon(@RequestBody Coupon coupon, @RequestHeader String token) throws ResponseStatusException {
		try {
			this.compService.updateCoupon(coupon);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

}
