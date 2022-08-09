package com.project.accesssystememulator.domain.exception;

/**
 * @author ilyin
 * @since 08.08.2022
 */

public class EntityNotFoundDbException extends RuntimeException {
    public EntityNotFoundDbException(String message) {
        super(message + "not found ");
    }
}

