package me.despical.purchasechecker.services.discord;

import lombok.AllArgsConstructor;
import me.despical.purchasechecker.configs.DiscordConfig;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * @author Despical
 * <p>
 * Created at 29.04.2025
 */
@Service
@AllArgsConstructor
public class DiscordService {

    private final DiscordConfig config;
    private final RestTemplate restTemplate;

    public String getRedirectUrl() {
        return String.format(
            "redirect:" +
                "https://discord.com/oauth2/authorize" +
                "?client_id=%s" +
                "&redirect_uri=%s" +
                "&response_type=code&scope=identify",
            config.getClientId(), config.getRedirectUri());
    }

    public Map<String, Object> fetchUserInfoFromCode(String code) {
        String accessToken = getAccessToken(code);
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);

        HttpEntity<Void> entity = new HttpEntity<>(headers);
        ResponseEntity<Map> response = restTemplate.exchange(
            "https://discord.com/api/v10/users/@me",
            HttpMethod.GET,
            entity,
            Map.class
        );

        return response.getBody();
    }

    private String getAccessToken(String code) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> tokenRequest = new LinkedMultiValueMap<>();
        tokenRequest.add("client_id", config.getClientId());
        tokenRequest.add("client_secret", config.getClientSecret());
        tokenRequest.add("grant_type", "authorization_code");
        tokenRequest.add("code", code);
        tokenRequest.add("redirect_uri", config.getRedirectUri());

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(tokenRequest, headers);
        ResponseEntity<Map> response = restTemplate.exchange(
            "https://discord.com/api/v10/oauth2/token",
            HttpMethod.POST,
            request,
            Map.class
        );

        return (String) response.getBody().get("access_token");
    }

    public void assignRoleToUser(String userId, String role) {
        String url = String.format(
            "https://discord.com/api/v10/guilds/%s/members/%s/roles/%s",
            config.getGuildId(),
            userId,
            role
        );

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bot " + config.getBotToken());
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Void> request = new HttpEntity<>(headers);
        restTemplate.exchange(url, HttpMethod.PUT, request, Void.class);
    }

    public String getRoleForPlugin(Long pluginId) {
        return config.getRoles().get(pluginId);
    }
}
