package com.hclspringsecurity.exceptions;

public class InvalidCredentialException extends RuntimeException {

   private static final long serialVersionUID = 1L;

   private String username;

   public InvalidCredentialException() {
      super();
   }

   public InvalidCredentialException(String username) {
      super();
      this.username = username;
   }

   public String getUsername() {
      return username;
   }
}
