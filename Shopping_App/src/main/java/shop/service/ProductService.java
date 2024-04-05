package shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import shop.dao.ProductRep;
import shop.entity.Product;

@Component
public class ProductService {
	@Autowired
	private ProductRep productRep;
	
	// get all orders
	public List<Product> getAllProduct(){
		
		List<Product> list = (List<Product>)this.productRep.findAll();
		return list;
		
	}
	//get single order by id
	
	public Product getProductById(int id){
		Product product= null;
		try {
			product =this.productRep.findById(id);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return product;
	}
	
	
	// adding product using post
	public Product AddProduct(Product p){
	Product product=this.productRep.save(p);
	return product;
		
	}
	
	// delete product
	public void DeleteProduct(int pid) {
		this.productRep.deleteById((long) pid);
		  
	}
	// upadte product
	public void UpadteProduct(Product product, int productId) {
		product.setId(productId);
		this.productRep.save(product);
	}
	
//	// Place a new order
//    Order placeOrder(Long userId, Long productId, int quantity, Long couponCode);
//    
//    // Get all orders for a given user
//    List<Order> getUserOrders(Long userId);
//    
//    // Get the status of a specific order
//    Order getOrderStatus(Long orderId);
}
