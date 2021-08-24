package com.assement.task.config.security;


import com.assement.task.config.exception.CustomException;
import com.assement.task.model.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Component
@Slf4j
public class JwtTokenProvider {

  @Autowired
  private JwtConfigProperties jwtConfigProperties;

  private String token1;

  private String secretKey;

  private long validityInMilliseconds;

  @Autowired
  private MyUserDetails myUserDetails;

  @PostConstruct
  protected void init() {
    this.secretKey = jwtConfigProperties.getSecret();
    this.validityInMilliseconds = jwtConfigProperties.getToken_validity();
    log.info("Secret Key -> ", this.secretKey);
    log.info("Validity -> ", this.validityInMilliseconds);
    this.secretKey = Base64.getEncoder().encodeToString(this.secretKey.getBytes());
  }

  public String createToken(String username, List<Role> roles) {

    Claims claims = Jwts.claims().setSubject(username);
    claims.put("auth", roles.stream().map(s -> new SimpleGrantedAuthority(s.getAuthority())).filter(Objects::nonNull).collect(Collectors.toList()));

    Date now = new Date();
    Date validity = new Date(now.getTime() + this.validityInMilliseconds);

    return Jwts.builder()//
        .setClaims(claims)//
        .setIssuedAt(now)//
        .setExpiration(validity)//
        .signWith(SignatureAlgorithm.HS256, secretKey)//
        .compact();
  }

  public Authentication getAuthentication(String token) {
    UserDetails userDetails = myUserDetails.loadUserByUsername(getUsername(token));

    return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
  }

  public String getUsername(String token) {
    return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
  }
  //this method will accept bearer token and return current user
  public String getCurrentUserSession(String bearerToken){
    return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(resolveToken(bearerToken)).getBody().getSubject();
  }
  public String resolveToken(String bearerToken) {


    if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
      this.token1 = bearerToken.substring(7);
      return bearerToken.substring(7);
    }
    else {
      return null;
    }
  }

  public boolean validateToken(String token) {
    try {
      Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
      return true;
    } catch (JwtException | IllegalArgumentException e) {
      throw new CustomException("Expired or invalid JWT token", HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

}

