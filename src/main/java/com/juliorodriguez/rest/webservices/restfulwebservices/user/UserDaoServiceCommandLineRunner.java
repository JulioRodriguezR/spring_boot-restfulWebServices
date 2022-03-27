package com.juliorodriguez.rest.webservices.restfulwebservices.user;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class UserDaoServiceCommandLineRunner implements CommandLineRunner {
    
   private static final Logger log =
        LoggerFactory.getLogger(UserDaoServiceCommandLineRunner.class);

   @Autowired
   private UserDaoService userDaoService;

   @Override 
   public void run(String... arg0) throws Exception {
      User user = new User(1, "NewUser", new Date()); 
      User insert = userDaoService.save(user);
      log.info("New User is created: " + user);
   }


}
