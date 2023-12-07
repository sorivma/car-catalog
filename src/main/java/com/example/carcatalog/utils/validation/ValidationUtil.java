package com.example.carcatalog.utils.validation;

import jakarta.validation.ConstraintViolation;

import java.util.Set;
/**
 * Validation utility interface.
 */
public interface ValidationUtil {
    /**
     * Checks if an object is valid.
     * @param object the object
     * @param <E> the type of the object
     * @return false if the object is valid, true otherwise
     */
    <E> boolean isInvalid(E object);

    /**
     * Returns a set of constraint violations.
     * @param object the object
     * @return the set of constraint violations
     * @param <E> the type of the object
     */
    <E> Set<ConstraintViolation<E>> violations(E object);
}
