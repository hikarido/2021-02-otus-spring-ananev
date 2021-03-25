package ru.otus.homework2.i18n;

public interface LocalisationService {
    String localize(String assetKey, Object... args);
}
