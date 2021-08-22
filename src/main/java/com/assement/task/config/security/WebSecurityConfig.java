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
         http
                 .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/dashboard").permitAll()
                .antMatchers("/users").permitAll()
                .antMatchers("/edit").permitAll()
                .antMatchers("/profile").permitAll()
                .antMatchers("/header").permitAll()
                .antMatchers("/do_register").permitAll()
                .antMatchers("/add_user").permitAll()
                .antMatchers("/search_user/**").permitAll()
                .antMatchers("/edit/**").permitAll()//
                .antMatchers("/update-user/**").permitAll()//
                .antMatchers("/delete-user/**").permitAll()
                .antMatchers("/admin/**").hasAuthority("ADMIN").anyRequest()
                .authenticated().and().csrf().disable();
                 // If a user try to access a resource without having enough permissions
                 http.exceptionHandling().accessDeniedPage("/login");

                 //Apply JWT
                 http.apply(new JwtTokenFilterConfigurer(jwtTokenProvider));
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**/**", "/js/ajax/**","/images/**");
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
