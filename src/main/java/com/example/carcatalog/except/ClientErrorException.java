package com.example.carcatalog.except;

/**
 * Base class for all client errors.
 */
public abstract class ClientErrorException extends RuntimeException {
    /**
     * Constructor.
     * @param message the message
     * @param args the arguments for the message
     */
    public ClientErrorException(String message, Object... args) {
        super(String.format(message, args));
    }

    /**
     * Exception for when an entity is not found.
     */
    public static class EntityNotFoundException extends ClientErrorException {
        private static final String MESSAGE = "%s with provided %s: [%s] not found";

        /**
         * Constructor.
         * @param entityName - the name of the entity that is not found
         * @param fieldName - the name of the field that is used to search for the entity
         * @param providedValue - the provided value for the field by client
         */
        public EntityNotFoundException(String entityName, String fieldName, String providedValue) {
            super(MESSAGE, entityName, fieldName, providedValue);
        }
    }
}
