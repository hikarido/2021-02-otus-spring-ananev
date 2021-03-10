package ru.otus.homework2.implementations.naive;

import ru.otus.homework2.core.ResourceAccessor;
import ru.otus.homework2.core.ThereIsNoSuchResource;

import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ResourceAccessorImpl implements ResourceAccessor {
    @Override
    public Path getResourcePath(String resourceIdentifier) {
        URL url = getClass().getClassLoader().getResource(resourceIdentifier);
        if (url == null) {
            throw new ThereIsNoSuchResource
                    .Builder("Can't find resource: ")
                    .withResourceName(resourceIdentifier)
                    .build();
        }

        return Paths.get(url.getPath());
    }
}
