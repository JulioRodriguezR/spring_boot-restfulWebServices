package com.juliorodriguez.rest.webservices.restfulwebservices.user;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Post {

  @Id
  @GeneratedValue
  private Integer id;
  
  private String description;

  @ManyToOne(fetch = FetchType.LAZY)
  @JsonIgnore
  private User user;

  public Integer getId() {
    return id;
  }

  public String getdescription() {
    return description;
  }

  public User  getUser() {
    return user;
  }

  public void setdescription(String description) {
    this.description = description;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public void setUser(User user) {
    this.user= user;
  }
  
}
