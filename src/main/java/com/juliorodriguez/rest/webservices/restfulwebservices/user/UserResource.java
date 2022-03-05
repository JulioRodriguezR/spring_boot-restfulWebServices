package com.juliorodriguez.rest.webservices.restfulwebservices.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserResource {
	
	@Autowired
	private UserDaoService srv;
	
	@GetMapping(path="/users")
	public List<User> retriveAllUsers() {
		return srv.findAll(); 
	}
	
	@GetMapping(path="/users/{id}")
	public User retriveAllUsers(@PathVariable int id) {
		return srv.findOne(id); 
	}
	
}
