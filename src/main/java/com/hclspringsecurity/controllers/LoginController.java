package com.hclspringsecurity.controllers;

import com.hclspringsecurity.entities.User;
import com.hclspringsecurity.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {

   @Autowired
   UserService userService;

   @GetMapping(value={"/", "/login"})
   public String home(Model model) {
      model.addAttribute("user", new User());
      return "home";
   }

   @PostMapping({"/", "login"})
   public ModelAndView login(HttpServletRequest request) {
      User user = (User) request.getAttribute("user");

      ModelAndView mav = new ModelAndView("landing");
      mav.addObject("user", user);

      return mav;
   }

   @GetMapping("/landing")
   public String landing() {
      return "landing";
   }
}
