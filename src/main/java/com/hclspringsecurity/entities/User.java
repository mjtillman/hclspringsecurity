package com.hclspringsecurity.entities;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

   @Column
   private String password;

   private SimpleGrantedAuthority roles;

   public User() {}

   public User(String username, String email, String password) {
      this.username = username;
      this.email = email;
      this.password = password;
      this.roles = new SimpleGrantedAuthority("USER");
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

   public boolean getActive() {
      return true;
   }

   public SimpleGrantedAuthority getRole() {
      return role;
   }
}
