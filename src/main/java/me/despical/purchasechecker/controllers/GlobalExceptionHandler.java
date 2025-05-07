package me.despical.purchasechecker.controllers;

import lombok.AllArgsConstructor;
import me.despical.purchasechecker.entities.VerificationError;
import me.despical.purchasechecker.mappers.ErrorMapper;
import me.despical.purchasechecker.repositories.ErrorRepository;
import me.despical.purchasechecker.services.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Despical
 * <p>
 * Created at 20.04.2025
 */
@ControllerAdvice
@AllArgsConstructor
public class GlobalExceptionHandler {

    private final JwtService jwtService;
    private final ErrorMapper errorMapper;
    private final ErrorRepository errorRepository;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationErrors(
        MethodArgumentNotValidException exception
    ) {
        Map<String, String> errors = new HashMap<>();
        exception.getBindingResult().getFieldErrors()
            .forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));

        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(Exception.class)
    public String handleException(Exception exception) {
        String exceptionMessage;

        if (exception.getClass() == NoResourceFoundException.class) {
            exceptionMessage = ErrorController.PAGE_NOT_FOUND;
        } else {
            exceptionMessage = exception.getMessage();
        }

        String token = jwtService.generateToken(null);
        VerificationError error = errorMapper.toEntity(token, exceptionMessage);
        errorRepository.save(error);

        return "redirect:/errors/" + token;
    }
}
