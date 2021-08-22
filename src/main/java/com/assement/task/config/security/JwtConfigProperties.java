package com.assement.task.config.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "jwt")
@Configuration
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtConfigProperties {

    private String secret;
    private long token_validity;
}
