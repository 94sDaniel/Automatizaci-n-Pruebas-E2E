package com.orangehrm.automation.commons;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

public final class ResourceHelper {

    private ResourceHelper() {
    }

    public static String imagePathOrPlaceholder(String relativePath) {

        URL resource = ResourceHelper.class.getClassLoader().getResource(relativePath);
        if (resource != null) {
            try {
                return Paths.get(resource.toURI()).toAbsolutePath().toString();
            } catch (URISyntaxException ignored) {
            }
        }

        Path localPath = Paths.get(relativePath);
        if (Files.exists(localPath)) {
            return localPath.toAbsolutePath().toString();
        }

        Path outputDir = Paths.get("target", "test-images");
        Path placeholder = outputDir.resolve(Paths.get(relativePath).getFileName().toString());

        try {
            Files.createDirectories(outputDir);
            if (Files.notExists(placeholder)) {
                byte[] pngBytes = Base64.getDecoder().decode(PLACEHOLDER_BASE64_PNG);
                Files.write(placeholder, pngBytes);
            }
        } catch (IOException ignored) {
            return Paths.get(relativePath).toAbsolutePath().toString();
        }

        return placeholder.toAbsolutePath().toString();
    }

    private static final String PLACEHOLDER_BASE64_PNG =
            "iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAQAAAC1HAwCAAAAC0lEQVR42mP8/xcAAn8BNXFa" +
                    "PgAAAABJRU5ErkJggg==";
}