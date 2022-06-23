package app.core.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

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
public class Customer {
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	
	@JsonIgnore
	@ManyToMany(cascade = {CascadeType.PERSIST,

			CascadeType.DETACH,

			CascadeType.MERGE, 

			CascadeType.REFRESH, })
	@JoinTable(

			name = "customer_coupon",

			joinColumns = @JoinColumn(name = "customer_id" ),
			inverseJoinColumns = @JoinColumn(name = "coupon_id")
//			,
//			uniqueConstraints = {@UniqueConstraint(columnNames = { "customer_id,coupon_id" }) })
)
	
			
	private List <Coupon> coupons;
	
}
