package me.despical.purchasechecker.controllers;

import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import me.despical.purchasechecker.entities.User;
import me.despical.purchasechecker.entities.Verification;
import me.despical.purchasechecker.exceptions.InvalidTokenException;
import me.despical.purchasechecker.exceptions.NoSuchRoleException;
import me.despical.purchasechecker.mappers.UserMapper;
import me.despical.purchasechecker.repositories.UserRepository;
import me.despical.purchasechecker.repositories.VerificationRepository;
import me.despical.purchasechecker.services.discord.DiscordJwt;
import me.despical.purchasechecker.services.discord.DiscordJwtService;
import me.despical.purchasechecker.services.discord.DiscordService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * @author Despical
 * <p>
 * Created at 7.05.2025
 */
@Controller
@AllArgsConstructor
public class DiscordController {

    private final DiscordService discordService;
    private final DiscordJwtService discordJwtService;
    private final VerificationRepository verificationRepository;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @PostMapping("/generate-token")
    public ResponseEntity<String> generateVerifyToken(
        @RequestParam String userId,
        @RequestParam String resourceId
    ) {
        return ResponseEntity.ok(discordJwtService.generateVerifyToken(userId, resourceId));
    }

    @GetMapping("/verify")
    public String verifyToken(@RequestParam String token, HttpSession session) {
        DiscordJwt jwt = discordJwtService.parseToken(token);

        if (jwt == null || jwt.isExpired()) {
            throw new InvalidTokenException();
        }

        session.setAttribute("discordJwt", jwt);
        return discordService.getRedirectUrl();
    }

    @GetMapping("/callback")
    public String handleCallback(@RequestParam String code, HttpSession session) {
        DiscordJwt jwt = (DiscordJwt) session.getAttribute("discordJwt");

        if (jwt == null) {
            throw new InvalidTokenException();
        }

        Long userId = jwt.getUserId();
        Map<String, Object> userInfo = discordService.fetchUserInfoFromCode(code);

        User user = userRepository.findById(userId).orElseGet(() -> {
            String username = (String) userInfo.get("username");
            User newUser = userMapper.toEntity(userId, username);

            userRepository.save(newUser);
            return newUser;
        });

        Long pluginId = jwt.getPluginId();
        boolean alreadyVerified = verificationRepository.existsByUserIdAndPluginId(userId, pluginId);

        if (alreadyVerified) {
            return "redirect:/already-verified";
        }

        verificationRepository.save(new Verification(user, pluginId));

        String role = discordService.getRoleForPlugin(pluginId);

        if (role == null) {
            throw new NoSuchRoleException(pluginId);
        }

        String discordUserId = (String) userInfo.get("id");
        discordService.assignRoleToUser(discordUserId, role);
        return "redirect:/success";
    }
}
