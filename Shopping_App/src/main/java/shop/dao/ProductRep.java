package shop.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import shop.entity.Product;

public interface ProductRep extends JpaRepository<Product, Long>{

    // Find a product by its ID
    public Product findById(long id);
//    
//    // Get all products
//    List<Product> findAll();
//    
//    // Save a product
//    void save(Product product);
//    
//    // Update a product
//    void update(Product product);
//    
//    // Delete a product
//    void delete(Product product);
}
