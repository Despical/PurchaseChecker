package me.despical.purchasechecker.controllers;

import lombok.AllArgsConstructor;
import me.despical.purchasechecker.exceptions.NoSuchDocumentationException;
import me.despical.purchasechecker.services.DocumentationService;
import me.despical.purchasechecker.utils.WordUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author Despical
 * <p>
 * Created at 18.04.2025
 */
@Controller
@AllArgsConstructor
public class HomeController {

    private final DocumentationService documentationService;

    @GetMapping({"/docs/{title}", "/docs/{title}/{sub-title}"})
    public String showDoc(
        @PathVariable("title") String title,
        @PathVariable(name = "sub-title", required = false) String subtitle,
        Model model) {

        String content = documentationService.getContent(title, subtitle);

        if (content == null) {
            throw new NoSuchDocumentationException();
        }

        model.addAttribute("title", WordUtils.capitalizeFirstLetters(title));
        model.addAttribute("content", content);
        return "documentation";
    }
}

