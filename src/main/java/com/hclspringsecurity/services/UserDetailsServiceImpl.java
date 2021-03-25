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
      List<GrantedAuthority> auths = getUserAuths(user.getRole());
      return buildUserForAuthentication(user, auths);
   }

   private List<GrantedAuthority> getUserAuths(SimpleGrantedAuthority role) {
      List<GrantedAuthority> auths = new ArrayList<>();
      auths.add(role);
      return auths;
   }

   private UserDetails buildUserForAuthentication(User user, List<GrantedAuthority> auths) {
      return new org.springframework.security.core.userdetails.User(user.getUsername(),
            user.getPassword(), user.getActive(), true, true,
            true, auths);
   }
}
