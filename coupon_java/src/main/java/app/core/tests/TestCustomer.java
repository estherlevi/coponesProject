//package app.core.tests;
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.core.annotation.Order;
//
//import app.core.entities.Coupon.Category;
//import app.core.exceptions.CouponSystemException;
//import app.core.login.ClientType;
//import app.core.login.LoginManager;
//import app.core.services.CustomerService;
//
////@Component
//@Order(3)
//	public class TestCustomer implements CommandLineRunner {
//	
//	CustomerService service;
//	@Autowired
//	LoginManager login;
//		@Override
//		public void run(String... args) throws Exception {
//
//			System.out.println("\n=======================Customer Test ==================");
//
//			try {
//				// test steps is defined in separate methods
//				System.out.println("**********doLogin***Started**********");
//				 doLogin();
//				System.out.println("**********doLogin***Ended**********");
//				System.out.println("\n**********doAddCouponPurchase***Started**********");
//				doAddCouponPurchase();
//				System.out.println("**********doAddCouponPurchase***Ended**********");
//				System.out.println("\n**********doGetAllCoupons***Started**********");
//				doGetAllCoupons();
//				System.out.println("**********doGetAllCoupons***Ended**********");
//				System.out.println("\n**********doGetCouponByCategory***Started**********");
//				doGetCouponByCategory();
//				System.out.println("**********doGetCouponByCategory***Ended**********");
//				System.out.println("\n**********doGetCouponByMaxPrice***Started**********");
//				doGetCouponByMaxPrice();
//				System.out.println("**********doGetCouponByMaxPrice***Ended**********");
//				System.out.println("\n**********doGetCompanyInfo***Started**********");
//				doGetCustomerInf0();
//				System.out.println("**********doGetCompanyInfo***Ended**********");
//				
//			} catch (Exception e) {
//				System.out.println("Error: " + e.getMessage());
//			}
//		}
//
//		
//		private void doLogin() throws CouponSystemException {
//		try {
//			
//		service = (CustomerService)	this.login.login("marc@mail.com","facebook",ClientType.CUSTOMER);
//		} catch (Exception e) {
//					
//				throw new CouponSystemException("Error in loging into customer: " + e.getMessage());
//			}
//		}
//
//
//		private void doAddCouponPurchase()  {
//	
//			try {
//			//	service.addCouponPurchase(service.getAllCouponsOfCustomer().get(0).getId());
//				
//				service.addCouponPurchase(2);
//				service.addCouponPurchase(3);
//				service.addCouponPurchase(4);
//				service.addCouponPurchase(5);
//				System.out.println("coupon purchase succesfully completed customer");
//			} catch (CouponSystemException e) {
//				System.out.println("do add coupon failed "+e.getMessage());
//			}
//		}
//
//	
//		private void doGetAllCoupons()  {
//			try {
//				service.getAllCouponsOfCustomer();
//				
//			} catch (CouponSystemException e) {
//				
//				System.out.println("failed to get coupons "+e.getMessage());
//			}
//			
//		}
//
//		private void doGetCouponByCategory() {
//			try {
//				service.getCouponByCategory(Category.FOOD);
//			} catch (CouponSystemException e) {
//				
//				System.out.println("failed to get coupons " +e.getMessage());
//			}			
//		}
//		
//		
//		private void doGetCouponByMaxPrice()  {
//			try {
//				service.getAllCouponByMaxPrice(80);
//			} catch (CouponSystemException e) {
//				
//				System.out.println("failed to get coupon by max price"+e.getMessage());
//			}			
//			}
//		
//
//		private void doGetCustomerInf0() {
//			try {
//				service.getCustomerInfo();
//			} catch (CouponSystemException e) {
//			System.out.println("failed to get customer info "+e.getMessage());
//			}
//			
//		}
//		
//}
