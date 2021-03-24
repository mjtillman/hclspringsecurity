package com.hclspringsecurity.controllers;

import com.hclspringsecurity.entities.User;
import com.hclspringsecurity.exceptions.InvalidCredentialException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class InvalidCredentialExceptionController {

   @ExceptionHandler(value = InvalidCredentialException.class)
   public ModelAndView loginError(InvalidCredentialException ex) {

      String errMsg1 = "Invalid user credentials.";
      String errMsg2 = "Please try again.";

      ModelAndView mav = new ModelAndView("home");
      mav.addObject("user", new User());
      mav.addObject("errMsg1", errMsg1);
      mav.addObject("errMsg2", errMsg2);

      return mav;
   }
}