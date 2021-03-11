package ru.otus.homework2.implementations.naive;

import ru.otus.homework2.core.ResourceAccessor;
import ru.otus.homework2.core.ThereIsNoSuchResource;

import java.io.InputStream;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ResourceAccessorImpl implements ResourceAccessor {
    @Override
    public InputStream getResourceInputStream(String resourceIdentifier) {
        InputStream stream = getClass().getClassLoader().getResourceAsStream(resourceIdentifier);
        if (stream == null) {
            throw new ThereIsNoSuchResource
                    .Builder("Can't get access or resource not exist: ")
                    .withResourceName(resourceIdentifier)
                    .build();
        }

        return stream;
    }
}
