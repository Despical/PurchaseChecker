package me.despical.purchasechecker.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import lombok.AllArgsConstructor;
import me.despical.purchasechecker.configs.JwtConfig;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

/**
 * @author Despical
 * <p>
 * Created at 2.05.2025
 */
@Service
@AllArgsConstructor
public class JwtService {

    private JwtConfig config;

    public String generateToken(Map<String, Object> claimMap) {
        Date now = new Date();
        Date expiration = new Date(now.getTime() + 1000L * 60 * 60 * 24);

        Claims claims = Jwts.claims()
            .add(claimMap)
            .issuedAt(now)
            .expiration(expiration)
            .build();

        return Jwts.builder().claims(claims).signWith(config.getJwtSecret()).compact();
    }

    public Claims parseToken(String token) {
        try {
            return Jwts.parser()
                .verifyWith(config.getJwtSecret())
                .build()
                .parseSignedClaims(token)
                .getPayload();
        } catch (JwtException exception) {
            return null;
        }
    }
}
