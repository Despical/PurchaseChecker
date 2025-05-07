package me.despical.purchasechecker.services.discord;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import javax.crypto.SecretKey;
import java.util.Date;

/**
 * @author Despical
 * <p>
 * Created at 30.04.2025
 */
public record DiscordJwt(Claims claims, SecretKey secretKey) {

    public boolean isExpired() {
        return claims.getExpiration().before(new Date());
    }

    public Date getExpiration() {
        return new Date(claims.getExpiration().getTime());
    }

    public Long getUserId() {
        return Long.valueOf(claims.getSubject());
    }

    public Long getPluginId() {
        return Long.valueOf(claims.get("pluginId", String.class));
    }

    @Override
    public String toString() {
        return Jwts.builder().claims(claims).signWith(secretKey).compact();
    }
}
