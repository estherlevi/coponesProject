package app.core.services;

import org.springframework.beans.factory.annotation.Autowired;

import app.core.exceptions.CouponSystemException;
import app.core.repasitories.CompanyRepository;
import app.core.repasitories.CouponRepository;
import app.core.repasitories.CustomerRepository;



public abstract class ClientService {
	@Autowired
	protected  CompanyRepository companyRepo;
	@Autowired
	protected  CouponRepository couponRepo;
	@Autowired
	protected  CustomerRepository customerRepo;
	
/**
 * system login 
 * @param email
 * @param password
 * @return
 * @throws CouponSystemException
 */
	public abstract boolean login(String email, String password) throws CouponSystemException;
	

}
