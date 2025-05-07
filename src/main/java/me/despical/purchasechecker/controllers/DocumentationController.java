package me.despical.purchasechecker.controllers;

import lombok.AllArgsConstructor;
import me.despical.purchasechecker.services.DocumentationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author Despical
 * <p>
 * Created at 7.05.2025
 */
@RestController
@RequestMapping("/api/v1/docs")
@AllArgsConstructor
public class DocumentationController {

    private final DocumentationService documentationService;

    @GetMapping
    public Map<String, List<String>> getDocumentationFileNames() {
        return documentationService.getDocumentations();
    }
}
