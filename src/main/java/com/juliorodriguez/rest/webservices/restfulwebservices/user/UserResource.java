package com.juliorodriguez.rest.webservices.restfulwebservices.user;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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

	@GetMapping(path = "/users")
	public List<User> retriveAllUsers() {
		return srv.findAll();
	}

	@GetMapping(path = "/users/{id}")
	public EntityModel<User> retriveOneUser(@PathVariable int id) {
		User user = srv.findOne(id);

		if (user == null) {
			throw new UserNotFoundException("id- " + id);
		}

		EntityModel<User> model = EntityModel.of(user);

		// Add link to entity model
		WebMvcLinkBuilder linkToUsers = linkTo(methodOn(this.getClass()).retriveAllUsers());
		model.add(linkToUsers.withRel("all-users"));

		return model;
	}

	@PostMapping(path = "/users")
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
		// Don't pass id in the path, backEnd work
		User savedUser = srv.save(user);

		// CREATED
		// resource location created savedUser.getId()
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedUser.getId()).toUri();

		return ResponseEntity.created(location).build();
	}

	@DeleteMapping(path = "/users/{id}")
	public void deleteUser(@PathVariable int id) {
		User user = srv.deletedById(id);
		if (user == null) {
			throw new UserNotFoundException("id- " + id);
		}
	}

}
