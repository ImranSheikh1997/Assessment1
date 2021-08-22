/*

package com.assement.task.config.security;

import com.shopiingbackend.blessmysales.dao.AdminRepository;
import com.shopiingbackend.blessmysales.model.entity.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetails implements UserDetailsService {

  @Autowired
  private AdminRepository adminRepository;

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    final Admin admin = adminRepository.findByEmail(email).get();

    if (admin == null) {
      throw new UsernameNotFoundException("User '" + admin.getEmail() + "' not found");
    }

    return org.springframework.security.core.userdetails.User//
        .withUsername(email)//
        .password(admin.getPassword())//
        .authorities(admin.getRoles())//
        .accountExpired(false)//
        .accountLocked(false)//
        .credentialsExpired(false)//
        .disabled(false)//
        .build();
  }


}

*/
