/*
package com.assement.task.config.security;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@AllArgsConstructor
@NoArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  private JwtTokenProvider jwtTokenProvider;

  @Override
  protected void configure(HttpSecurity http) throws Exception {

    // Disable CSRF (cross site request forgery)
    http.csrf().disable();
    http.cors().disable();
    http.headers().frameOptions().sameOrigin().and().
            authorizeRequests();
    // No session will be created or used by spring security
    http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    // Entry points
    http.authorizeRequests()//
            .antMatchers("/bms/admin/login").permitAll()//
            .antMatchers("/bms/lookup/add").permitAll()//
            .antMatchers("/bms/lookup/findlookup/**").permitAll()//
            .antMatchers("/bms/**").permitAll()//
            .antMatchers("/bms/lookup/**").permitAll()//
            .antMatchers("/bms/lookup/delete-all").permitAll()
            .antMatchers("/bms/lookup/delete/*").permitAll()
            .antMatchers("/bms/lookup/update").permitAll()
            .antMatchers("/bms/lookup/findall").permitAll()//
            .antMatchers("/bms/lookupchild/add").permitAll()//
            .antMatchers("/bms/lookupchild/findlookup/*").permitAll()//
            .antMatchers("/bms/lookupchild/delete-all").permitAll()
            .antMatchers("/bms/lookupchild/delete/*").permitAll()
            .antMatchers("/bms/lookupchild/update").permitAll()
            .antMatchers("/bms/lookupchild/findall").permitAll()//
            .antMatchers("/bms/formlist/findall").permitAll()//
            .antMatchers("/bms/business-panel/**").permitAll()
            .antMatchers("/loop-example").permitAll()//
            .antMatchers("/condition").permitAll()//
        .antMatchers("/users/signin").permitAll()//
        .antMatchers("/users/signup").permitAll()//

            // Disallow everything else..
        .anyRequest().authenticated();

    // If a user try to access a resource without having enough permissions
    http.exceptionHandling().accessDeniedPage("/login");

     //Apply JWT
    http.apply(new JwtTokenFilterConfigurer(jwtTokenProvider));

    // Optional, if you want to test the API from a browser
    // http.httpBasic();
  }

  @Override
  public void configure(WebSecurity web) throws Exception {
    // Allow swagger to be accessed without authentication
    web.ignoring().antMatchers("/v3/api-docs")//
        .antMatchers("/swagger-resources/**")//


  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder(12);
  }

  @Override
  @Bean
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

  @Bean
  public ModelMapper modelMapper() {
    return new ModelMapper();
  }
}
*/