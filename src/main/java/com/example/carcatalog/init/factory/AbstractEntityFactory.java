package com.example.carcatalog.init.factory;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Base class for all entity factories.
 *
 * @param <T> the type of the entity
 */
public abstract class AbstractEntityFactory<T> {
    /**
     * Generates an entity.
     *
     * @return the generated entity
     */
    public abstract T getEntity();

    /**
     * Generates a collection of entities.
     *
     * @param size the size of the collection
     * @return the generated collection
     * @throws RuntimeException if the size is greater than the maximum allowed size.
     */
    public List<T> getEntities(int size) throws RuntimeException {
        if (getMaxSize() < size) {
            throw new RuntimeException(
                    String.format("Could not generate collection of entities of size: [%s] " +
                                    "due to provided limitation: [%s]",
                            size, getMaxSize())
            );
        }

        return IntStream.range(0, size)
                .mapToObj(number -> getEntity())
                .collect(Collectors.toList());
    }

    /**
     * Method to specify the maximum allowed size of the collection of entities.
     * @return the maximum allowed size of the collection of entities
     */
    public abstract int getMaxSize();
}
