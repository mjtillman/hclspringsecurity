package com.hclspringsecurity.services;

import com.hclspringsecurity.entities.User;
import com.hclspringsecurity.exceptions.InvalidCredentialException;
import com.hclspringsecurity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

   @Autowired
   private UserRepository userRepo;

   public Iterable<User> getAllUsers() {
      return userRepo.findAll();
   }

   public User getUserByName(String name) throws InvalidCredentialException {
      Optional<User> foundUser = Optional.ofNullable(userRepo.findByUsername(name));

      if (!foundUser.isPresent()) {
         throw new InvalidCredentialException(name);
      }

      return foundUser.get();
   }

   public User updateUser(User updateUser) {
      return userRepo.save(updateUser);
   }
}
