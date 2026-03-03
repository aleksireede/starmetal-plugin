package net.jonnegaming.starMetal;

import org.bukkit.configuration.file.FileConfiguration;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class VersionChecker {
    private static final Pattern TAG_PATTERN = Pattern.compile("\"tag_name\"\\s*:\\s*\"([^\"]+)\"");

    private VersionChecker() {
    }

    public static void checkForUpdates(StarMetal plugin) {
        FileConfiguration config = plugin.getConfig();
        if (!config.getBoolean("version-checker.enabled", true)) {
            return;
        }

        String apiUrl = config.getString(
                "version-checker.api-url",
                "https://api.github.com/repos/aleksireede/starmetal-plugin/releases/latest"
        );
        if (apiUrl.isBlank()) {
            plugin.getLogger().warning("Version checker is enabled, but version-checker.api-url is blank.");
            return;
        }

        plugin.getServer().getAsyncScheduler().runNow(plugin, task -> {
            try {
                String latestVersion = fetchLatestVersion(apiUrl);
                if (latestVersion == null) {
                    plugin.getLogger().warning("Version checker could not read the latest release version.");
                    return;
                }

                String currentVersion = plugin.getPluginMeta().getVersion();
                if (isNewerVersion(latestVersion, currentVersion)) {
                    plugin.getLogger().warning("A new StarMetal version is available: " + latestVersion + " (current: " + currentVersion + ")");
                    plugin.getLogger().warning("Download it from https://github.com/aleksireede/starmetal-plugin/releases");
                }
            } catch (Exception exception) {
                plugin.getLogger().warning("Version checker failed: " + exception.getMessage());
            }
        });
    }

    private static String fetchLatestVersion(String apiUrl) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(10))
                .build();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(apiUrl))
                .timeout(Duration.ofSeconds(10))
                .header("Accept", "application/vnd.github+json")
                .header("User-Agent", "StarMetal-VersionChecker")
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() < 200 || response.statusCode() >= 300) {
            throw new IOException("HTTP " + response.statusCode());
        }

        Matcher matcher = TAG_PATTERN.matcher(response.body());
        if (!matcher.find()) {
            return null;
        }

        return matcher.group(1);
    }

    static boolean isNewerVersion(String latestVersion, String currentVersion) {
        int[] latest = parseVersion(latestVersion);
        int[] current = parseVersion(currentVersion);
        int maxLength = Math.max(latest.length, current.length);

        for (int i = 0; i < maxLength; i++) {
            int latestPart = i < latest.length ? latest[i] : 0;
            int currentPart = i < current.length ? current[i] : 0;
            if (latestPart > currentPart) {
                return true;
            }
            if (latestPart < currentPart) {
                return false;
            }
        }

        return false;
    }

    private static int[] parseVersion(String version) {
        String normalized = version == null ? "" : version.trim().toLowerCase();
        if (normalized.startsWith("v")) {
            normalized = normalized.substring(1);
        }

        String[] rawParts = normalized.split("\\.");
        int[] parts = new int[rawParts.length];
        for (int i = 0; i < rawParts.length; i++) {
            String digits = rawParts[i].replaceAll("[^0-9].*$", "");
            if (digits.isEmpty()) {
                parts[i] = 0;
            } else {
                parts[i] = Integer.parseInt(digits);
            }
        }
        return parts;
    }
}
