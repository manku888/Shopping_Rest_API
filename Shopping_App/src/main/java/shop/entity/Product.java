package shop.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(name = "ProductName")
	private String name;
	@Column(name = "ProductQuentity")
	private int quentity;
	@Column(name = "ProductPrice")
	private double price;
	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Product(long id, String name, int quentity, double price) {
		super();
		this.id = id;
		this.name = name;
		this.quentity = quentity;
		this.price = price;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getQuentity() {
		return quentity;
	}
	public void setQuentity(int quentity) {
		this.quentity = quentity;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	

	
	

}
