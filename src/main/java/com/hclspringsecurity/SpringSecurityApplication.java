package com.hclspringsecurity;

import com.hclspringsecurity.controllers.InvalidCredentialExceptionController;
import com.hclspringsecurity.controllers.LoginController;
import com.hclspringsecurity.entities.Role;
import com.hclspringsecurity.entities.User;
import com.hclspringsecurity.services.UserDetailsServiceImpl;
import com.hclspringsecurity.services.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({
      InvalidCredentialExceptionController.class,
      LoginController.class,
      Role.class,
      User.class,
      UserDetailsServiceImpl.class,
      UserService.class
})
public class SpringSecurityApplication {
   public static void main(String[] args) {
      SpringApplication.run(SpringSecurityApplication.class, args);
   }
}
