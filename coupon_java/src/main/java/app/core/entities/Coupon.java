package app.core.entities;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString(exclude={"customers","company"})
@Entity
public class Coupon {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne (cascade = {CascadeType.PERSIST,

			CascadeType.DETACH,

			CascadeType.MERGE, 

			CascadeType.REFRESH, })
	@JsonIgnore
	@JoinColumn (name = "company_Id")
	private Company company;
	@Enumerated(EnumType.STRING)
	private Category category;
	private String title;
	private String description;
	private LocalDate startDate;
	private LocalDate endDate;
	private int amount;
	private double price;
	
	private String image;
	
	

	public enum Category{
		FOOD,ELECTRONICS,HOME,CLOTHING,CAMPING,VACATION;
	}

	@ManyToMany (cascade = {CascadeType.PERSIST,

			CascadeType.DETACH,

			CascadeType.MERGE, 

			CascadeType.REFRESH, })
	@JoinTable(

			name = "customer_coupon",

			joinColumns = @JoinColumn(name = "coupon_id" ),
			inverseJoinColumns = @JoinColumn(name = "customer_id")
			
//			,uniqueConstraints = {@UniqueConstraint(columnNames = { "customer_id,coupon_id" })})
	
			)

	@JsonIgnore
	private List <Customer> customers;

	public Coupon(int id, Company company, Category category, String title, String description, LocalDate startDate,
			LocalDate endDate, int amount, double price, String image) {
		super();
		this.id = id;
		this.company = company;
		this.category = category;
		this.title = title;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
		this.amount = amount;
		this.price = price;
		this.image = image;
	}

	}
	

