package me.despical.purchasechecker.configs;

import io.jsonwebtoken.security.Keys;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;

/**
 * @author Despical
 * <p>
 * Created at 2.05.2025
 */
@Data
@Component
@ConfigurationProperties(prefix = "spring.jwt")
public class JwtConfig {

    private String secret;

    @Bean
    public SecretKey getJwtSecret() {
        return Keys.hmacShaKeyFor(secret.getBytes());
    }
}
