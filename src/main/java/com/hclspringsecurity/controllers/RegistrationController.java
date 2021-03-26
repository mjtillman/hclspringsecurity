package com.hclspringsecurity.controllers;

import com.hclspringsecurity.entities.User;
import com.hclspringsecurity.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import javax.validation.Valid;

@Controller
public class RegistrationController {

   @Autowired
   UserService userService;

   @GetMapping("/register")
   public ModelAndView getRegistration() {
      ModelAndView mav = new ModelAndView("register");
      mav.addObject("user", new User());
      return mav;
   }

   @PostMapping("/register")
   public ModelAndView registerUser(@Valid User user, BindingResult bindingResult) {

      ModelAndView mav = new ModelAndView();
      User foundUser = userService.findUserByUsername(user.getUsername());

      if (foundUser != null) {
         bindingResult.rejectValue("userName", "error.user",
               "There is already a user registered with the user name provided");
      }

      if (bindingResult.hasErrors()) {
         mav.setViewName("register");
      } else {
         userService.updateUser(user);
         mav.addObject("successMessage", "User has been registered successfully");
         mav.addObject("user", new User());
         mav.setViewName("home");
      }

      return mav;
   }
}
