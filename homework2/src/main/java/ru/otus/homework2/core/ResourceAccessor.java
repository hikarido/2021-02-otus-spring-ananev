package ru.otus.homework2.core;
import java.io.InputStream;
import java.nio.file.Path;

/**
 * provides facilities of accessing to system resources
 */
public interface ResourceAccessor {
    /**
     * Tries get input stream to resource. Returned resource must be closed by calling side.
     * @param resourceIdentifier name of resource line "exercises.csv"
     * @return input stream to resource.
     * @throws ThereIsNoSuchResource if can't get resource path
     * may be this resource name not exist or has typos
     */
    InputStream getResourceInputStream(String resourceIdentifier);
}
