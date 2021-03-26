package com.hclspringsecurity.security;

import com.hclspringsecurity.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

   @Autowired
   private BCryptPasswordEncoder encoder;

   @Autowired
   private UserDetailsServiceImpl userDetailsService;

   @Override
   protected void configure(AuthenticationManagerBuilder auth) throws Exception {
      auth
            .userDetailsService(userDetailsService)
            .passwordEncoder(encoder);
   }

   @Override
   protected void configure(HttpSecurity http) throws Exception {
      http
            .authorizeRequests()
               .antMatchers("/", "/login", "/registration").permitAll()
               .antMatchers("/landing").access("hasRole('USER')")
            .anyRequest().authenticated()
               .and().csrf().disable()
            .formLogin()
               .loginPage("/login")
               .successHandler(authenticationSuccessHandler())
               .failureUrl("/")
            .and().logout()
            .logoutSuccessUrl("/");
   }

   @Bean
   public AuthenticationSuccessHandler authenticationSuccessHandler(){
      return new AuthSuccessHandlerImpl();
   }

   @Override
   public void configure(WebSecurity web) throws Exception {
      web
            .ignoring()
            .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**");
   }
}
