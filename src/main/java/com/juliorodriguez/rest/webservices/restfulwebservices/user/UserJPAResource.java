package com.juliorodriguez.rest.webservices.restfulwebservices.user;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UserJPAResource {

  @Autowired
  private UserDaoService srv;

  @Autowired
  private UserRepository userRepository;

  @GetMapping(path = "/jpa/users")
  public List<User> retrieveAllUsers() {
    return userRepository.findAll();
  }

  @GetMapping(path = "/jpa/users/{id}")
  public Optional<User> retriveUser(@PathVariable int id) {
    Optional<User> user = userRepository.findById(id);
    if (!user.isPresent()) {
      throw new UserNotFoundException("id- " + id);
    }
    // "all-users", SERVER_PATH + "/users"
    // retrieveAllUsers
    EntityModel<User> resource = EntityModel.of(user.get());// new EntityModel<User>(user.get());

    WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllUsers());

    resource.add(linkTo.withRel("all-users"));
    return user;
  }

  @PostMapping(path = "/jpa/users")
  public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
    // Don't pass id in the path, backEnd work
    User savedUser = userRepository.save(user);

    // CREATED
    // resource location created savedUser.getId()
    URI location = ServletUriComponentsBuilder
        .fromCurrentRequest()
        .path("/{id}")
        .buildAndExpand(savedUser.getId()).toUri();

    return ResponseEntity.created(location).build();
  }

  @DeleteMapping(path = "/jpa/users/{id}")
  public void deleteUser(@PathVariable int id) {
    userRepository.deleteById(id);
  }

}
