package utils;

import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import utils.CustomExceptions.ConfigurationChecked;

public class JsonConfigReader {

    private static JSONObject config;

    static {
        try {
            String path = System.getProperty("user.dir")
                    + "/src/test/resources/config.json";

            String content = new String(Files.readAllBytes(Paths.get(path)));
            config = new JSONObject(content);

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load config.json");
        }
    }

    public static String getEnv() {
        return config.getString("env");
    }

    public static String getBrowser() {
        return config.getString("browser");
    }


    public static boolean isHeadless() {
        // Allow -Dheadless=true to override JSON if provided
        String sysProp = config.getString("headless");
        if (sysProp != null && !sysProp.isBlank()) {
            System.out.println("headless is--"+sysProp);
            return Boolean.parseBoolean(sysProp);
        }
        return config.optBoolean("headless", false);
    }


    public static String getBaseUrl() {

        String env = getEnv();
        JSONObject urls = config.getJSONObject("urls");

        return urls.getString(env);
    }


    /** baseUrl must be present and start with http/https */
    public static String requireBaseUrl() throws ConfigurationChecked {
        // prefer explicit -DbaseUrl if set; else get from urls map using env
        String explicit = System.getProperty("baseUrl");
        String url = (explicit != null && !explicit.isBlank()) ? explicit : getBaseUrl();

        if (url == null || url.isBlank()) {
            String env = getEnv();
            throw new CustomExceptions.ConfigurationChecked("Configuration error: baseUrl is missing for env='" + env + "'. " +
                    "Provide urls.{env} in config.json or -DbaseUrl=...");
        }
        if (!url.startsWith("http://") && !url.startsWith("https://")) {
            throw new CustomExceptions.ConfigurationChecked("Configuration error: baseUrl must start with http/https. Provided: " + url);
        }
        return url;
    }

}
