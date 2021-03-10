package ru.otus.homework2.core;
import java.nio.file.Path;

/**
 * provides facilities of accessing to system resources
 */
public interface ResourceAccessor {
    /**
     * Tries get path to required resource
     * @param resourceIdentifier name of resource line "exercises.csv"
     * @return path to resource
     * @throws ThereIsNoSuchResource if can't get resource path
     * may be this resource name not exist or has typos
     */
    Path getResourcePath(String resourceIdentifier);
}
