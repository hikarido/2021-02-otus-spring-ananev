package ru.otus.homework2.implementations.naive;

import ru.otus.homework2.core.ResourceAccessor;
import ru.otus.homework2.core.CantGetAccessToResource;

import java.io.InputStream;

public class ResourceAccessorImpl implements ResourceAccessor {
    @Override
    public InputStream getResourceInputStream(String resourceIdentifier) {
        InputStream stream = getClass().getClassLoader().getResourceAsStream(resourceIdentifier);
        if (stream == null) {
            throw new CantGetAccessToResource
                    .Builder("Can't get access or resource not exist: ")
                    .withResourceName(resourceIdentifier)
                    .build();
        }

        return stream;
    }
}
