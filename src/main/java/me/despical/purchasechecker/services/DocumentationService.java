package me.despical.purchasechecker.services;

import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.ast.Node;
import lombok.Getter;
import me.despical.purchasechecker.utils.WordUtils;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Despical
 * <p>
 * Created at 7.05.2025
 */
@Component
public class DocumentationService {

    private final Path docsFolder;
    private final Parser parser;
    private final HtmlRenderer htmlRenderer;
    private final Map<String, String> htmlCache;
    private final @Getter Map<String, List<String>> documentations;

    public DocumentationService() throws IOException {
        this.docsFolder = Path.of("src", "main", "resources", "docs");
        this.parser = Parser.builder().build();
        this.htmlRenderer = HtmlRenderer.builder().build();
        this.htmlCache = new HashMap<>();
        this.documentations = getDocumentationFileNames();
    }

    private Map<String, List<String>> getDocumentationFileNames() throws IOException {
        Map<String, List<String>> documentations = new HashMap<>();

        try (var directories = Files.newDirectoryStream(docsFolder, Files::isDirectory)) {
            for (Path folder : directories) {
                List<String> subtitles = Files.list(folder)
                    .filter(Files::isRegularFile)
                    .filter(path -> path.toString().endsWith(".md"))
                    .map(path -> path.getFileName().toString().replace(".md", ""))
                    .map(path -> WordUtils.capitalizeFirstLetters(path, WordUtils.REMOVE_HYPHEN))
                    .toList();

                String folderName = folder.getFileName().toString();
                documentations.put(WordUtils.capitalizeFirstLetters(folderName, WordUtils.REMOVE_HYPHEN), subtitles);
            }
        }

        return Map.copyOf(documentations);
    }

    public String getContent(String title, String subtitle) {
        if (subtitle != null) {
            title = title + File.separator + subtitle;
        }

        return htmlCache.computeIfAbsent(title, key -> {
            try {
                Path docFile = docsFolder.resolve(key + ".md");

                if (!Files.exists(docFile)) {
                    return null;
                }

                String markdown = Files.readString(docFile);
                Node document = parser.parse(markdown);
                return htmlRenderer.render(document);
            } catch (IOException exception) {
                throw new UncheckedIOException(exception);
            }
        });
    }
}
