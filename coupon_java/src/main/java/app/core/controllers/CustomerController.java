package app.core.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import app.core.entities.Coupon;
import app.core.entities.Coupon.Category;
import app.core.entities.Customer;
import app.core.exceptions.CouponSystemException;
import app.core.services.CustomerService;
import app.core.services.JwtUtil;

@RestController
@RequestMapping("/api/customer")
@CrossOrigin
public class CustomerController {

	private CustomerService customerService;
	private JwtUtil jwtUtil;

	public CustomerController(CustomerService customerService, JwtUtil jwtUtil) {
		super();
		this.customerService = customerService;
		this.jwtUtil = jwtUtil;
	}

	@PostMapping("/addCouponPurchase")
	public void addCouponPurchase(@RequestBody Coupon coupon, @RequestHeader String token) {
		try {
			this.customerService.addCouponPurchase(coupon.getId(), jwtUtil.getId(token));
		} catch (CouponSystemException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	@GetMapping("/allCouponsByPrice/{maxPrice}")
	public List<Coupon> getCouponByMaxPrice(@PathVariable double maxPrice, @RequestHeader String token)
			throws ResponseStatusException {
		try {
			return this.customerService.getAllCouponByMaxPrice(maxPrice);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

	@GetMapping("/allCoupons")
	public List<Coupon> getAllCoupons(@RequestHeader String token) {
		try {
			return this.customerService.getAllCouponsOfCustomer(jwtUtil.getId(token));
		} catch (CouponSystemException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

	@GetMapping("/customerInfo")
	public Customer getCustomrInfo(@RequestHeader String token) throws ResponseStatusException {
		try {
			return this.customerService.getCustomerInfo(jwtUtil.getId(token));
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

	@GetMapping("/allCouponsByCategory/{categoryName}")
	public List<Coupon> getCouponByCategory(@PathVariable Category categoryName, @RequestHeader String token)
			throws ResponseStatusException {
		try {
			System.out.println("categoryName " + categoryName);
			return this.customerService.getCouponByCategory(categoryName);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

}
