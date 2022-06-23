package app.core.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EqualsAndHashCode(of = "id")
@ToString(exclude = "coupons")
public class Company {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String email;
	private String password;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "company")
//	@JoinColumn
	@JsonIgnore
	private List<Coupon>coupons;
	
	
	public Company(String email, String password) {
		
		this.email = email;
		this.password = password;
		
		
	}


	public Company(String name, String email, String password) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
	}


	public Company(int id, String name, String email, String password) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
	}
	
	
	// add coupon to company
	public void add(Coupon coupon) {
		if (this.coupons == null) {
			this.coupons = new ArrayList<>();
		}
			coupon.setCompany(this);
			this.coupons.add(coupon);
		}
	}


