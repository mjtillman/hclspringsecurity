package com.hclspringsecurity.repository;

import com.hclspringsecurity.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

   User findByUsername(String username);
   User findByEmail(String email);
}
