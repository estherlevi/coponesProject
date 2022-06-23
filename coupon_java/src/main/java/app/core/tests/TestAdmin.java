package app.core.tests;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;

import app.core.entities.Company;
import app.core.entities.Customer;
import app.core.exceptions.CouponSystemException;
import app.core.login.ClientType;
import app.core.login.LoginManager;
import app.core.services.AdminService;


//@Component
@Order(1)
public class TestAdmin implements CommandLineRunner{

	
	AdminService service;
	@Autowired
	LoginManager login;
	@Override
	public void run(String... args) throws Exception {
		

			System.out.println("\n================Admin Test ==================");

			try {
				
				System.out.println("**********doLogin***started**********");
				doLogin();
				System.out.println("**********doLogin***Ended**********");
				System.out.println("\n**********DoAddCompany***started**********");
				doAddCompany();
				System.out.println("**********doAddCompany***Ended**********");
				System.out.println("\n**********doUpdateCompany***Started**********");
				doUpdatecompany();
				System.out.println("**********doUpdateCompany***Ended**********");
				System.out.println("\n**********doDeletCompany***Started**********");
				doDeleteCompany();
				System.out.println("**********doDeletCompany***Ended**********");
				System.out.println("\n**********doGetAllCompanies***Started**********");
				doGetAllCompanies();
				System.out.println("**********doGetAllCompanies***Ended**********");
				System.out.println("\n**********doGetCompanyById***Started**********");
				doGetCompanyById();
				System.out.println("**********doGetCompanyById***Ended**********");
				System.out.println("\n**********doAddcustomer***Started**********");
				doAddCustomer();
				System.out.println("**********doAddcustomer***Ended**********");
				System.out.println("\n**********doUpdateCustomer***Started**********");
				doUpdateCustomer();
				System.out.println("**********doUpdateCustomer***Ended**********");
				System.out.println("\n**********doDeleteCustomer***Started**********");
				doDeleteCustomer();
				System.out.println("**********doDeleteCustomer***Ended**********");
				System.out.println("\n**********doGetallCustomers***Started**********");
				doGetAllCustomers();
				System.out.println("**********doGetallCustomers***Ended**********");
				System.out.println("\n**********doGetCustomerById***Started**********");
				doGetCustomerById();
				System.out.println("**********doGetCustomerById***Ended**********");
				
			} catch (Exception e) {
				System.out.println("Error: " + e.getMessage());
			}

	}
	
		private void doLogin() throws CouponSystemException {
		
			try {
				service=(AdminService)this.login.login("admin@admin.com", "admin",ClientType.ADMINISTRATOR);
			} catch (CouponSystemException e) {
				
				throw new CouponSystemException("Error: " + e.getMessage());
			}
					
		
		}

		private void doAddCompany() throws CouponSystemException{
			Company c1 = new Company(0, "Cloud", "office@cloud.com", "cloudPass");
			Company c2 = new Company(0, "Spring", "office@spring.com", "springPass");
			Company c3 = new Company(0, "Meta", "office@meta.com", "metaPass");
			Company c4 = new Company(0, "Web", "office@web.com", "webPass");
			try {
				service.addCompany(c1);
				service.addCompany(c2);
				service.addCompany(c3);
				service.addCompany(c4);
			} catch (CouponSystemException e) {
				throw new CouponSystemException("failed to add company with name:"+c1.getName() ,e);
			}
			System.out.println("company added");
		}
		
	

	private void doUpdatecompany() throws CouponSystemException {
		try {
			
		Company company = new Company(4, "Web", "office@web.com", "1234Pass");
		service.updateCompany(company);
		} catch (Exception e) {
			System.out.println("failed to update company"+e.getMessage());
		}
		
	}

		private void doDeleteCompany() throws Exception {
			try{
			service.deleteCompany(2);
			} catch (Exception e) {
				System.out.println("failed to delete company"+e.getMessage());
			}
			
			System.out.println("company deleted");
		}

		private List<Company> doGetAllCompanies() throws CouponSystemException {
			try {
			List<Company> companies = service.getAllCompanies();
				if(companies.isEmpty()) {
					throw new CouponSystemException("There are no exsisting companies");
				} else {
				System.out.println(companies);	
			return companies;
	}
			}
				 catch (CouponSystemException e) {
						System.out.println("failed to get companeis " +e.getMessage());
						
					}
			return null;		

}
		
		private Company doGetCompanyById() throws CouponSystemException {
			try {
				Company company = service.getCompanyById(4);
				System.out.println(company);
				return company;
			} catch (CouponSystemException e) {
				System.out.println("failed to get company"+e.getMessage());
			}
			return null;
		}
	
		private void doAddCustomer() throws CouponSystemException{
			Customer cus1 =new Customer(0, "Dan", "Israeli","dan@mail.com","12345",null);
			Customer cus2 =new Customer(0, "Larry", "Page","lpage@mail.com","google",null);
			Customer cus3 =new Customer(0, "Sergey", "Brin","sbrin@mail.com","google",null);
			
//			Coupon coupon1 = new Coupon(0, null, Category.HOME, "computerScreen", "monitor for laptop", null, null, 0, 0, null);
//			Coupon coupon2 = new Coupon(0, null, null, Category.ELECTRONICS, null, null, 0, 0, null);
//			ArrayList<Coupon> listCoupon = new ArrayList<Coupon>();
//			listCoupon.add(coupon1);
//			listCoupon.add(coupon2);
			Customer cus4 =new Customer(0, "Marc", "Zuckerberg","mzuckerberg@mail.com","facebook",null);
			service.addCustomer(cus1);
			service.addCustomer(cus2);
			service.addCustomer(cus3);
			service.addCustomer(cus4);
			
			
		}
		private void doDeleteCustomer() throws CouponSystemException {
		try {
			service.deleteCustomer(2);
					
			System.out.println("customer deleted");
		} catch (Exception e) {
			System.out.println("failed to delete customer"+e.getMessage());
		}
		}


	
			
			private void doUpdateCustomer() throws CouponSystemException {
				try {
				Customer customer = new Customer(4, "Marc", "Zuckerberg","marc@mail.com","facebook",null);
				service.updateCustomer(customer);
					
				} catch (Exception e) {
					System.out.println("failed to update customer"+e.getMessage());
				}	
				
			}
	




			private List<Customer> doGetAllCustomers() throws CouponSystemException {
				try {
					List<Customer> customers = service.getAllCustomers();
						if(customers.isEmpty()) {
							throw new CouponSystemException("There are no exsisting customers");
						} else {
						System.out.println(customers);	
					return customers;
			}
					}
						 catch (CouponSystemException e) {
								System.out.println("failed to get customers" +e.getMessage());
								
							}		
				return null;
			
		}

	
			private Customer doGetCustomerById() throws CouponSystemException {
					try {
						Customer customer = service.getCustomerById(3);
						System.out.println(customer);
						return customer;
					} catch (CouponSystemException e) {
						System.out.println("failed to get customer"+e.getMessage());
					}
					return null;
				
						}
}
	