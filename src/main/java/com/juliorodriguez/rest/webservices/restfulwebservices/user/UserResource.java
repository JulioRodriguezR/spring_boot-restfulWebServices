package com.juliorodriguez.rest.webservices.restfulwebservices.user;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
		User user = srv.findOne(id);
		if (user == null) {
			throw new UserNotFoundException("id- " + id);
		}
		return user;
	}
	
	@PostMapping(path="/users")
	public ResponseEntity<User> createUser(@RequestBody User user) {
		// Don't pass id in the path, backEnd work
		User savedUser = srv.save(user);
		
		// CREATED
		// resource location created  savedUser.getId()
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedUser.getId()).toUri();
		
		return ResponseEntity.created(location).build();
	}
	
}
