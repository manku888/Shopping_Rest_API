package shop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import shop.entity.Product;
import shop.errormsges.ProductErrorMessage;
import shop.service.ProductService;

@RestController
public class ProductController {
	@Autowired
	private ProductService productService;

	// get all products
	@GetMapping("/products")
	public ResponseEntity<List<Product>> getallproduct() {

		List<Product> list = productService.getAllProduct();
		try {
			if (list.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		return ResponseEntity.status(HttpStatus.OK).body(list);

	}

	// get product using id
	@GetMapping("/products/{id}")
	public ResponseEntity<Object> getproductById(@PathVariable("id") int id) {

		Product product = productService.getProductById(id);
		try {
			if (product == null) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body(new ProductErrorMessage("Product with ID " + id + " is not available"));
				
				
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		return ResponseEntity.status(HttpStatus.OK).body(product);

	}

	// post product
	@PostMapping(value = "/products", consumes = "application/json")
	public ResponseEntity<Product> AddProduct(@RequestBody Product product) {
		Product p = null;
		try {
			p = this.productService.AddProduct(product);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

		return ResponseEntity.status(HttpStatus.CREATED).body(p);
	

	}
	// delete product by id..

	@DeleteMapping("products/{productId}")
	public ResponseEntity<String> DeleteProduct(@PathVariable("productId") int productId) {
		try {
			if (productId!=0) {
				
				this.productService.DeleteProduct(productId);
			}
		} catch (Exception e) {
			
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

		return ResponseEntity.status(HttpStatus.CREATED).body("Product is Deleted Succsfully "+"product id is : "+productId);

	}

	// update product by id
	@PutMapping("products/{productId}")
	public ResponseEntity<String> UpdateProduct(@RequestBody Product product ,@PathVariable ("productId") int productId) {
		try {
			
			this.productService.UpadteProduct(product, productId);
			return ResponseEntity.status(HttpStatus.CREATED).body("Product is Updated Succsfully "+"product id is : "+productId);
			
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("OOpss...Unable to update product");
			
		}
	}
	
}
