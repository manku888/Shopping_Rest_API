package shop.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import shop.entity.User;
import shop.errormsges.ProductErrorMessage;
import shop.service.UserService;
@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	
	// get all users
	@GetMapping("/api/user")
	public ResponseEntity<List<User>> getAllUsers(){
		
	List<User> list	=userService.getAllUsers();
	
	try {
		if (list.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	} catch (Exception e) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
	return ResponseEntity.status(HttpStatus.OK).body(list);
	
    }
	
	
	// get user by id 
	@GetMapping("/api/user/{id}")
	public ResponseEntity<Object> getUserById(@PathVariable("id") Long id) {
	    Optional<User> user = userService.getUserById(id);
	    try {
	        if (!user.isPresent()) {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                    .body(new ProductErrorMessage("User with ID " + id + " is not available"));
	        }
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	    }
	    return ResponseEntity.status(HttpStatus.OK).body(user.get());
	}

	
	
	
	
	
	// post user
	@PostMapping(value = "/api/user", consumes = "application/json")
	public ResponseEntity<User> createUser(@RequestBody User user) {
	    try {
	        User createdUser = this.userService.createUser(user);
	        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
	    } catch (DataIntegrityViolationException e) {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	           
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	    }
	}

	
	// delete user
	@DeleteMapping("/api/user/{userId}")
	public ResponseEntity<String> deleteUser(@PathVariable("userId") Long userId) { 
	    try {
	        if (userId != 0) {
	            this.userService.deleteUser(userId);
	            return ResponseEntity.status(HttpStatus.OK).body("Deleted......Successfully");
	        }
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	    }

	    return ResponseEntity.notFound().build(); // Consider handling this case
	}

	
	// update user
	@PutMapping("/api/user/{id}")
	public ResponseEntity<String> updateUser(@PathVariable("id") Long id, @RequestBody User userDetails) {
	    try {
	        this.userService.updateUser(id, userDetails);
	        return ResponseEntity.status(HttpStatus.OK).body("User updated successfully");
	    } catch (IllegalArgumentException e) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found with ID: " + id);
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unable to update user");
	    }
	}

		 
		 
	}
	

