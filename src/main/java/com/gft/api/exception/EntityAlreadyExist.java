package com.gft.api.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EntityAlreadyExist extends RuntimeException{
    private String message;

    public EntityAlreadyExist(String message) {
        super(message);
        this.message = message;
    }
}
