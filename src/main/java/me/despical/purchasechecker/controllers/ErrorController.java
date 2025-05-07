package me.despical.purchasechecker.controllers;

import io.jsonwebtoken.Claims;
import lombok.AllArgsConstructor;
import me.despical.purchasechecker.entities.VerificationError;
import me.despical.purchasechecker.repositories.ErrorRepository;
import me.despical.purchasechecker.services.JwtService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Date;

/**
 * @author Despical
 * <p>
 * Created at 2.05.2025
 */
@Controller
@AllArgsConstructor
public class ErrorController {

    public static final String PAGE_NOT_FOUND = "The requested page could not be found.";

    private final JwtService jwtService;
    private final ErrorRepository errorRepository;

    @GetMapping("/errors/{token}")
    public String error(@PathVariable String token, Model model) {
        try {
            Claims claims = jwtService.parseToken(token);
            var errorOptional = errorRepository.findByToken(token);

            if (errorOptional.isEmpty() || claims.getExpiration().before(new Date())) {
                model.addAttribute("errorMessage", "The provided token is invalid or has expired.");
                return "error";
            }

            VerificationError error = errorOptional.orElseThrow();
            String message = error.getMessage();
            model.addAttribute("errorMessage", message);

            if (!message.equals(PAGE_NOT_FOUND)) {
                model.addAttribute("errorTime", error.getErrorTime());
            }
        } catch (Exception exception) {
            model.addAttribute("errorMessage", PAGE_NOT_FOUND);
        }

        return "error";
    }

    @GetMapping("/success")
    public String successPage() {
        return "success";
    }

    @GetMapping("/already-verified")
    public String alreadyVerifiedPage() {
        return "already-verified";
    }
}
