package com.assement.task.config;


import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.
                cors()
                .and()
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
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**/**", "/js/ajax/**","/images/**");
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }

    /*Have to create Bean for ModelMapper. as there is no other config class available. i am putting it over here.*/
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}