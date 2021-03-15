package ru.otus.homework3.core;
import java.io.InputStream;

/**
 * provides facilities of accessing to system resources
 */
public interface ResourceAccessor {
    /**
     * Tries get input stream to resource. Returned resource must be closed by calling side.
     * @param resourceIdentifier name of resource line "exercises.csv"
     * @return input stream to resource.
     * @throws CantGetAccessToResource if can't resource as stream. Access or rights problem
     */
    InputStream getResourceInputStream(String resourceIdentifier);
}
