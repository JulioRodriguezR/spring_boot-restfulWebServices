package com.juliorodriguez.rest.webservices.restfulwebservices.user;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Post {

  @Id
  @GeneratedValue
  private Integer id;
  private String desString;

  @ManyToOne(fetch = FetchType.LAZY)
  private User user;

  public Integer getId() {
    return id;
  }

  public String getDesString() {
    return desString;
  }

  public void setDesString(String desString) {
    this.desString = desString;
  }

  public void setId(Integer id) {
    this.id = id;
  }
  
}
