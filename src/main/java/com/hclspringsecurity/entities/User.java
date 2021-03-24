package com.hclspringsecurity.entities;

import javax.persistence.*;

@Entity
@Table(name="users")
public class User {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column
   private String username;

   @Column
   private String email;

   private String password;

   public User() {}

   public User(String username, String email, String password1) {
      this.username = username;
      this.email = email;
      this.password = password1;
   }

   public Long getId() {
      return id;
   }

   public String getUsername() {
      return username;
   }

   public void setUsername(String username) {
      this.username = username;
   }

   public String getEmail() {
      return email;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   public String getPassword() {
      return password;
   }

   public void setPassword(String password) {
      this.password = password;
   }
}
