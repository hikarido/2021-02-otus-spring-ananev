package ru.otus.homework2.i18n;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.otus.homework2.config.AppSettings;

import java.util.Locale;

@Service
public class LocalisationServiceImpl implements LocalisationService {

    MessageSource messageSource;
    AppSettings settings;

    public LocalisationServiceImpl(MessageSource messageSource, AppSettings settings) {
        this.messageSource = messageSource;
        this.settings = settings;
    }

    @Override
    public String localize(String assetKey, Object... args) {
        return messageSource.getMessage(assetKey, args, settings.getAppLocale());
    }
}
