package app.core.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import app.core.exceptions.CouponSystemException;
import app.core.services.AdminService;
import app.core.services.ClientService;
import app.core.services.CompanyService;
import app.core.services.CustomerService;

@Component
@Scope("singleton")
public class LoginManager {
	
	@Autowired
	CustomerService customerService;
	@Autowired
	AdminService adminService;
	@Autowired
	CompanyService companyService;
	private static LoginManager instance = null;
	
	
	// Constructor
	private LoginManager() throws CouponSystemException
	{
		
		
	}
	
	public static LoginManager getInstance() throws CouponSystemException {
		if (instance == null) {
			synchronized (LoginManager.class) {

				if (instance == null) {
					instance = new LoginManager();
				}
			}
		}
		return instance;
	}
	
	public ClientService login( String email, String password, ClientType clientType) throws CouponSystemException {
		
		switch(clientType)
		{
			case ADMINISTRATOR:
			{
				
				try {
					if(adminService.login(email, password) == false)
					{
						adminService = null;
						
					}
				} catch (CouponSystemException e) {
					throw new CouponSystemException("email or password not correct for this type of client",e);
				}
				return adminService;
			}
			case COMPANY:
			{
				
				try {
					if(companyService.login(email, password) == false)
					{
						companyService = null;
						
					}
				} catch (CouponSystemException e) {
					throw new CouponSystemException("email or password not correct for this type of client",e);
				}
				return companyService;
			}
			case CUSTOMER:
			{
				
				try {
					if(customerService.login(email, password) == false)
					{
						customerService = null;
						
					}
				} catch (CouponSystemException e) {
					throw new CouponSystemException("email or password not correct for this type of client",e);
				}
				return customerService;
			}
			default:
			{
				// invalid type
				return null;
			}
		}		
	}
	

}
