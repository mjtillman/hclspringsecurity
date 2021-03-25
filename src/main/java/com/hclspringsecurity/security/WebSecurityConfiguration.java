package com.hclspringsecurity.security;

import com.hclspringsecurity.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

   @Autowired
   private BCryptPasswordEncoder encoder;

   @Autowired
   private UserDetailsServiceImpl detailsService;

   @Override
   protected void configure(AuthenticationManagerBuilder auth) throws Exception {
      auth  .userDetailsService(detailsService)
            .passwordEncoder(encoder);
   }

   @Override
   protected void configure(HttpSecurity http) throws Exception {
      http  .authorizeRequests()
            .antMatchers("/").permitAll()
            .antMatchers("/register").permitAll()
            .authenticated().and().csrf().disable().formLogin()
            .loginPage("/").failureUrl("/")
            .defaultSuccessUrl("/landing")
            .usernameParameter("username")
            .passwordParameter("password")
            .and().logout()
            .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
            .logout("/").and().exceptionHandling()
            .accessDeniedPage("/");
   }

   @Override
   public void configure(WebSecurity web) throws Exception {
      web   .ignoring()
            .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**");
   }
}
