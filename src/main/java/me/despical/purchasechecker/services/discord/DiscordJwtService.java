package me.despical.purchasechecker.services.discord;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import lombok.AllArgsConstructor;
import me.despical.purchasechecker.configs.DiscordConfig;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author Despical
 * <p>
 * Created at 30.04.2025
 */
@Service
@AllArgsConstructor
public class DiscordJwtService {

    private final DiscordConfig config;

    public String generateVerifyToken(String userId, String pluginId) {
        Date date = new Date();
        Claims claims = Jwts.claims()
            .subject(userId)
            .add("pluginId", pluginId)
            .issuedAt(date)
            .expiration(new Date(date.getTime() + 1000L * config.getVerifyTokenExpiration()))
            .build();

        return new DiscordJwt(claims, config.getDiscordJwtSecret()).toString();
    }

    public DiscordJwt parseToken(String token) {
        try {
            Claims claims = Jwts.parser()
                .verifyWith(config.getDiscordJwtSecret())
                .build()
                .parseSignedClaims(token)
                .getPayload();

            return new DiscordJwt(claims, config.getDiscordJwtSecret());
        } catch (JwtException exception) {
            return null;
        }
    }
}
