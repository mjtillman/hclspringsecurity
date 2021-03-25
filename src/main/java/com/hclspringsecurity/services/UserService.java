package com.hclspringsecurity.services;

import com.hclspringsecurity.entities.Role;
import com.hclspringsecurity.entities.User;
import com.hclspringsecurity.repository.RoleRepository;
import com.hclspringsecurity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;

@Service
public class UserService {

   private final UserRepository userRepo;
   private final RoleRepository roleRepo;
   private final BCryptPasswordEncoder encoder;

   @Autowired
   public UserService(UserRepository userRepo,
                      RoleRepository roleRepo,
                      BCryptPasswordEncoder encoder) {
      this.userRepo = userRepo;
      this.roleRepo = roleRepo;
      this.encoder = encoder;
   }

   public Iterable<User> getAllUsers() {
      return userRepo.findAll();
   }

   public User findUserbyUsername(String name) throws UsernameNotFoundException {
      Optional<User> foundUser = Optional.ofNullable(userRepo.findByUsername(name));

      if (!foundUser.isPresent()) {
         throw new UsernameNotFoundException(name + " not found.");
      }

      return foundUser.get();
   }

   public User findUserByEmail(String email) {
      Optional<User> foundUser = Optional.ofNullable(userRepo.findByEmail(email));

      if (!foundUser.isPresent()) {
         throw new UsernameNotFoundException(email + " not found.");
      }
      return userRepo.findByEmail(email);
   }

   public User updateUser(User updateUser) {
      updateUser.setPassword(encoder.encode(updateUser.getPassword()));
      Role userRole = roleRepo.findByRole("user");
      updateUser.setRoles(new HashSet<>(Collections.singletonList(userRole)));
      return userRepo.save(updateUser);
   }
}
