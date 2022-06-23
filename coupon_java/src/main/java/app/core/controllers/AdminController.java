package app.core.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import app.core.entities.Company;
import app.core.entities.Customer;
import app.core.exceptions.CouponSystemException;
import app.core.services.AdminService;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin
public class AdminController {

	private AdminService adminService;

	public AdminController(AdminService adminService) {
		super();
		this.adminService = adminService;
	}

	@PostMapping("/company/add")
	public void addCompany(@RequestBody Company company, @RequestHeader String token) {
		try {
			this.adminService.addCompany(company);
		} catch (CouponSystemException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	@PostMapping("/customer/add")
	public void addCustomer(@RequestBody Customer customer) throws Exception {
		try {
			this.adminService.addCustomer(customer);
		} catch (CouponSystemException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	@GetMapping("/customer/{customerId}")
	public Customer getOneCustomer(@PathVariable int customerId, @RequestHeader String token)
			throws ResponseStatusException {
		try {
			return this.adminService.getCustomerById(customerId);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

	@GetMapping("/company/{companyId}")
	public Company getOneCompany(@PathVariable int companyId, @RequestHeader String token)
			throws ResponseStatusException {
		try {
			return this.adminService.getCompanyById(companyId);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

	@GetMapping("/customers/all")
	public List<Customer> getAllCustomers(@RequestHeader String token) {
		try {
			return this.adminService.getAllCustomers();
		} catch (CouponSystemException e) {

			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

	@GetMapping("/companies/all")
	public List<Company> getAllCompanies(@RequestHeader String token) {
		try {
			return this.adminService.getAllCompanies();
		} catch (CouponSystemException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

	@PutMapping("/customer/update")
	public void updateCustomer(@RequestBody Customer customer, @RequestHeader String token)
			throws ResponseStatusException {
		try {
			this.adminService.updateCustomer(customer);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

	@PutMapping("/company/update")
	public void updateCompany(@RequestBody Company company, @RequestHeader String token)
			throws ResponseStatusException {
		try {
			this.adminService.updateCompany(company);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

	@DeleteMapping("/company/{companyId}")
	public void deleteOneCompany(@PathVariable int companyId, @RequestHeader String token)
			throws ResponseStatusException {
		try {
			this.adminService.deleteCompany(companyId);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

	@DeleteMapping("/customer/{customerId}")
	public void deleteOneCustomer(@PathVariable int customerId, @RequestHeader String token)
			throws ResponseStatusException {
		try {
			this.adminService.deleteCustomer(customerId);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

}
