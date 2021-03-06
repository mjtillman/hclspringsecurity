package com.hclspringsecurity;

import com.hclspringsecurity.controllers.LoginController;
import com.hclspringsecurity.entities.User;
import com.hclspringsecurity.security.WebSecurityConfiguration;
import com.hclspringsecurity.services.UserDetailsServiceImpl;
import com.hclspringsecurity.services.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@Import({
      BCryptPasswordEncoder.class,
      LoginController.class,
      User.class,
      WebSecurityConfiguration.class,
      UserDetailsServiceImpl.class,
      UserService.class
})
public class SpringSecurityApplication {
   public static void main(String[] args) {
      SpringApplication.run(SpringSecurityApplication.class, args);
   }
}
