package com.hclspringsecurity.controllers;

import com.hclspringsecurity.entities.User;
import com.hclspringsecurity.exceptions.InvalidCredentialException;
import com.hclspringsecurity.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

   @Autowired
   UserService userService;

   @GetMapping("/")
   public String home(Model model) {
      model.addAttribute("user", new User());
      return "home";
   }

   @PostMapping("/")
   public ModelAndView submitLogin(@ModelAttribute User user) throws InvalidCredentialException {

      User checkUser;

      ModelAndView mav = new ModelAndView();

      checkUser = userService.getUserByName(user.getUsername());
      String password = user.getPassword();

      if (password.equals(checkUser.getPassword())) {
         mav.setViewName("landing");
         mav.addObject("name", checkUser.getUsername());
      } else {
         throw new InvalidCredentialException(user.getUsername());
      }

      return mav;
   }
}
