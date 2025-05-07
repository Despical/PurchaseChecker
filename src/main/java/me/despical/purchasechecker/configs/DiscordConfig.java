package me.despical.purchasechecker.configs;

import io.jsonwebtoken.security.Keys;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Map;

/**
 * @author Despical
 * <p>
 * Created at 29.04.2025
 */
@Data
@Component
@ConfigurationProperties(prefix = "discord")
public class DiscordConfig {

    private String clientId;
    private String clientSecret;
    private String redirectUri;
    private String botToken;
    private String guildId;
    private String secret;
    private Long verifyTokenExpiration;
    private Map<Long, String> roles;

    @Bean
    public SecretKey getDiscordJwtSecret() {
        return Keys.hmacShaKeyFor(secret.getBytes());
    }
}
