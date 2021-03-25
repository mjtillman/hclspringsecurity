package com.hclspringsecurity.services;

import com.hclspringsecurity.entities.User;
import com.sun.corba.se.impl.orbutil.graph.Graph;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

   @Autowired
   private UserService userService;

   @Override
   @Transactional
   public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      User user = userService.findUserbyUsername(username);
      List<SimpleGrantedAuthority> auth = new ArrayList<>(Collections.singletonList(new SimpleGrantedAuthority("USER")));
      return buildUserForAuthentication(user, auth);
   }

   private UserDetails buildUserForAuthentication(User user, SimpleGrantedAuthority auth) {
      return new org.springframework.security.core.userdetails.User(user.getUsername(),
            user.getPassword(), user.getActive(), true, true,
            true, new ArrayList<>(auth));
   }
}
