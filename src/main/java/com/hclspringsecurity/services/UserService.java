package com.hclspringsecurity.services;

import com.hclspringsecurity.entities.User;
import com.hclspringsecurity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

   private final UserRepository userRepo;
   private final BCryptPasswordEncoder encoder;

   @Autowired
   public UserService(UserRepository userRepo,
                      BCryptPasswordEncoder encoder) {
      this.userRepo = userRepo;
      this.encoder = encoder;
   }

   public Iterable<User> getAllUsers() {
      return userRepo.findAll();
   }

   public User findUserByUsername(String name) {
      return userRepo.findByUsername(name);
   }

   public User findUserByEmail(String email) {
      return userRepo.findByEmail(email);
   }

   public User updateUser(User updateUser) {
      updateUser.setPassword(encoder.encode(updateUser.getPassword()));
      return userRepo.save(updateUser);
   }
}
