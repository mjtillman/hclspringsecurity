package com.hclspringsecurity.security;

import com.hclspringsecurity.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

   @Autowired
   private BCryptPasswordEncoder encoder;

   @Autowired
   private UserDetailsServiceImpl detailsService;

   @Override
   protected void configure(AuthenticationManagerBuilder auth) throws Exception {
      auth.inMemoryAuthentication()
            .withUser("user")
            .password("password")
            .roles("USER");
   }

   @Override
   protected void configure(HttpSecurity http) throws Exception {
      http.authorizeRequests()
            .antMatchers("/login").hasAnyRole("USER")
            .antMatchers("/").permitAll()
            .and().formLogin()
            .defaultSuccessUrl("/landing", true)
            .and().logout()
            .logoutSuccessUrl("/");
   }

   @Bean
   public PasswordEncoder getPasswordEncoder() {
      return NoOpPasswordEncoder.getInstance();
   }
}
