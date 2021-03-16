package ru.otus.homework3.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Locale;

/**
 * app: application settings
 *   language-tag: tag of locale. may be {ru-RU, en-EN}, not required
 */
@ConfigurationProperties(prefix = "app")
public class AppSettingsImpl implements AppSettings {
    private String languageTag = "en-EN";
    private Locale locale;

    public void setLanguageTag(String languageTag) {
        this.languageTag = languageTag;
    }

    @Override
    public Locale getAppLocale() {
        if (locale == null) {
            this.locale = new Locale.Builder().setLanguageTag(languageTag).build();
        }
        return locale;
    }
}
