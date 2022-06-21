package com.gft.api.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EntityNotFoundException extends RuntimeException{
    private String message;

    public EntityNotFoundException(String message) {
        super(message);
        this.message = message;
    }
}
