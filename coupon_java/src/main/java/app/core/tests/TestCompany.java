//package app.core.tests;
//
//
//import java.time.LocalDate;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.core.annotation.Order;
//
//import app.core.entities.Company;
//import app.core.entities.Coupon;
//import app.core.entities.Coupon.Category;
//import app.core.exceptions.CouponSystemException;
//import app.core.login.ClientType;
//import app.core.login.LoginManager;
//import app.core.services.CompanyService;
//
////@Component
//@Order(2)
//	public class TestCompany implements CommandLineRunner {
//	
//	CompanyService service;
//	@Autowired
//	LoginManager login;
//		@Override
//		public void run(String... args) throws Exception {
//
//			System.out.println("\n======================Company Test ==================");
//
//			try {
//				// test steps is defined in separate methods
//				System.out.println("**********doLogin***Started**********");
//				 doLogin();
//				System.out.println("**********doLogin***Ended**********");
//				System.out.println("\n**********doAddCoupon***Started**********");
//				doAddCoupon();
//				System.out.println("**********doAddCoupon***Ended**********");
//				System.out.println("\n**********doUpdateCoupon***Started**********");
//				doUpdateCoupon();
//				System.out.println("**********doUpdateCoupon***Ended**********");
//				System.out.println("\n**********doDeleteCoupon***Started**********");
//				doDeleteCoupon();
//				System.out.println("**********doDeleteCoupon***Ended**********");
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
//				doGetCompanyInf0();
//				System.out.println("**********doGetCompanyInfo***Ended**********");
//			} catch (Exception e) {
//				System.out.println("Error: " + e.getMessage());
//			}
//			}
//
//		
//
//		
//		private void doLogin() throws CouponSystemException {
//		try {
//			
//			service= (CompanyService)this.login.login("office@meta.com","metaPass",ClientType.COMPANY);
//			
//		} catch (Exception e) {
//					
//				throw new CouponSystemException("Error in loging into company: " + e.getMessage());
//			}
//		}
//
//		
//
//		private void doAddCoupon()  {
//	
////			try {
//			Coupon coupon1 = new Coupon(0, null, Category.CAMPING, "summerFum", "tent", LocalDate.of(2021, 12, 01), LocalDate.of(2022, 01, 22),50, 150, null);
//			Coupon coupon2 = new Coupon(0,null, Category.ELECTRONICS, "headSet2", "wirelessHeadSet", LocalDate.of(2022, 01, 01), LocalDate.of(2022, 06, 12),500, 350, null);
//			Coupon coupon3 = new Coupon(0, new Company(3,null, null, null), Category.FOOD, "lunch", "Wolt -Cibus", LocalDate.of(2022, 01, 01), LocalDate.of(2022, 01, 31),500, 250, null);
//			Coupon coupon4 = new Coupon(0, new Company(3,null, null, null), Category.HOME, "webCamera", "webCam", LocalDate.of(2022, 01, 01), LocalDate.of(2022, 05, 31),1000, 80, null);
//			Coupon coupon5 = new Coupon(0, new Company(3,null, null, null), Category.HOME, "webMicrophone", "webMicro", LocalDate.of(2022, 01, 01), LocalDate.of(2022, 01, 15),1000, 80, null);
//			
////				service.addCoupon(coupon1);
////				service.addCoupon(coupon2);
////				service.addCoupon(coupon3);
////				service.addCoupon(coupon4);
////				service.addCoupon(coupon5);
//				System.out.println("coupons succesfuly added");
////			} 
////			catch (CouponSystemException e) {
////				System.out.println("do add coupon failed for the folowing"+e.getMessage());
////			}
//			
//		}
//
//		private void doUpdateCoupon()  {
//			Coupon coupon = new Coupon(3, null, Category.FOOD, "Breakfast", "Wolt -Cibus", LocalDate.of(2022, 01, 15), LocalDate.of(2022, 02, 28),400, 150,null);
//			
//			try {
//				service.updateCoupon(coupon);
//			} catch (CouponSystemException e) {
//				System.out.println("failed to update cooupon with name:"+e.getMessage());
//				
//			}
//			
//		}			
//		
//		private void doDeleteCoupon() {
//			service.deleteCoupon(1);
//			
//			System.out.println("coupon deleted");
//		}
//
//
//
//
//		private void doGetAllCoupons()  {
//			try {
//				//service.GetAllCoupons();
//				
//			} catch (CouponSystemException e) {
//				
//				System.out.println("failed to get coupons"+e.getMessage());
//			}
//			
//		}
//
//		private void doGetCouponByCategory() {
//			try {
//				service.GetCouponByCategory(Category.FOOD);
//				
//			} catch (CouponSystemException e) {
//				
//				System.out.println("failed to get coupons" +e.getMessage());
//			}			
//		}
//		
//		
//		private void doGetCouponByMaxPrice()  {
//			try {
//				//service.GetAllCouponByMaxPrice(80);
//			} catch (CouponSystemException e) {
//				
//				System.out.println("failed to get coupon by max price"+e.getMessage());
//			}			
//			}
//		
//
//		private void doGetCompanyInf0() {
//			try {
//				//service.getCompanyInfo();
//			} catch (CouponSystemException e) {
//			System.out.println("failed to get company info"+e.getMessage());
//			}
//			
//		}
//	}


