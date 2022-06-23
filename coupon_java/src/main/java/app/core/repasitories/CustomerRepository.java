package app.core.repasitories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import app.core.entities.Coupon;
import app.core.entities.Customer;


public interface CustomerRepository extends JpaRepository<Customer, Integer>{

	
	boolean existsByEmail(String email);

	Optional<Customer> findByEmailAndPassword(String email, String password);
	List<Coupon> findAllById(int customerId);
	boolean existsByIdAndCouponsId(int customerId, int couponId);

	
	
	
}
