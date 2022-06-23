package app.core.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import app.core.entities.Company;
import app.core.entities.Customer;
import app.core.exceptions.CouponSystemException;
import app.core.repasitories.CompanyRepository;
import app.core.repasitories.CouponRepository;
import app.core.repasitories.CustomerRepository;

@Service
@Transactional(rollbackFor = CouponSystemException.class)
// Singleton no scope
public class AdminService extends ClientService {

	// use with refresh. when using a List if dosn't work

	@Autowired
	private CompanyRepository compRepo;
	@Autowired
	private CouponRepository couponRepo;
	@Autowired
	private CustomerRepository customerRepo;

	@Value("${admin.email}")
	private String email;
	@Value("${admin.password}")
	private String password;

	@Override
	public boolean login(String email, String password) throws CouponSystemException {
		if (email.equals (this.email) && password.equals (this.password)) {
			System.out.println("Administrator successfully logged in");
			return true;
		} else {			
			throw new CouponSystemException("Wrong password or email for admin");
		}

	}

	public void addCompany(Company company) throws CouponSystemException {
		if(companyRepo.existsById(company.getId())) {
			throw new CouponSystemException("company ID already exsits");
			}
			
			else if  (!companyRepo.existsByName(company.getName()) && !companyRepo.existsByEmail(company.getEmail())) 
			 {

			companyRepo.save(company);
		} else {
			throw new CouponSystemException("can't add company with name:" + company.getName() + " and email:"
					+ company.getEmail() + " name or email of company already exsits");
		}
	}

	public void updateCompany(Company company) throws CouponSystemException {

		Optional<Company> opt = companyRepo.findById(company.getId());
		if (opt.isPresent()) {
			Company companyFromDb = opt.get();
			if (!companyFromDb.getName().equals(company.getName())) {

				throw new CouponSystemException("can't update compamy Name - action not allowed");
			}

			companyFromDb.setPassword(company.getPassword());
			companyFromDb.setEmail(company.getEmail());

		} else {
			throw new CouponSystemException("failed to update company with Id: " + company.getId());
		}
	}

	public void deleteCompany(int companyId) throws CouponSystemException {
		try {
			this.companyRepo.deleteById(companyId);
			System.out.println("deleted compnay: " + companyId);

		} catch (Exception e) {
			throw new CouponSystemException("eror in deleting company: " + companyId, e);
		}
	}

// findAll in CompanyRepo

	public List<Company> getAllCompanies() throws CouponSystemException {
		return companyRepo.findAll();
	}

// findById in CompanyRepo

	public Company getCompanyById(int companyId) throws CouponSystemException {

		Optional<Company> opt = this.compRepo.findById(companyId);
		if (opt.isPresent()) {
			return opt.get();
		} else {
			throw new CouponSystemException("getCompany failed -company" + companyId + " not found");
		}

	}

//add client
	public void addCustomer(Customer customer) throws CouponSystemException {
		
		
			if(customerRepo.existsById(customer.getId())) {
				throw new CouponSystemException("customer ID already exsits");
			}
			try {
			if (!customerRepo.existsByEmail(customer.getEmail())) {

				customerRepo.save(customer);

			} else {
				throw new CouponSystemException("customer with email : " + customer.getEmail() + "already exsits");
			}
		} catch (Exception e) {
			throw new CouponSystemException("can't add customer with name: " + customer.getFirstName() + " "+customer.getLastName() +" and email: "
					+ customer.getEmail() + " name or email of customer already exsits", e);
		}

	}

//delete existing client
	public void deleteCustomer(int customerId) throws CouponSystemException {
		try {
			customerRepo.deleteById(customerId);
			System.out.println("deleted customer: " + customerId);
		} catch (Exception e) {
			throw new CouponSystemException("couldn't delete customer #: " + customerId);
		}
	}

//update existing client
	public void updateCustomer(Customer customer) throws CouponSystemException {
	
			Optional<Customer> exsistingCustomer = customerRepo.findById(customer.getId());
			if (customer.getId() < 1 || exsistingCustomer.isEmpty()) {
				throw new CouponSystemException("invalid customer ID entered");
			}
			if(exsistingCustomer.isPresent()) {
				Customer customerFromDb=exsistingCustomer.get();
			
					customerFromDb.setFirstName(customer.getFirstName());
					customerFromDb.setLastName(customer.getLastName());
					customerFromDb.setPassword(customer.getPassword());

					customerFromDb.setEmail(customer.getEmail());
			
			}
			}

//findAll customers in ustomerRepo

	public List<Customer> getAllCustomers() throws CouponSystemException {
		return customerRepo.findAll();
	}

//findById in CustomerRepo

	public Customer getCustomerById(int customerId) throws CouponSystemException {

		Optional<Customer> opt = this.customerRepo.findById(customerId);
		if (opt.isPresent()) {
			return opt.get();
		} else {
			throw new CouponSystemException("getCompany failed -company" + customerId + " not found");
		}
	}
}
