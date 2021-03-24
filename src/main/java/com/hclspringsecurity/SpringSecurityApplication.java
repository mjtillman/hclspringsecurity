package com.hclspringsecurity;

import com.hclspringsecurity.controllers.InvalidCredentialExceptionController;
import com.hclspringsecurity.controllers.LoginController;
import com.hclspringsecurity.entities.User;
import com.hclspringsecurity.exceptions.InvalidCredentialException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({
      InvalidCredentialExceptionController.class,
      LoginController.class,
      InvalidCredentialException.class,
      User.class,
      InvalidCredentialException.class
})
public class SpringSecurityApplication {

   public static void main(String[] args) {
      SpringApplication.run(SpringSecurityApplication.class, args);
   }

}
