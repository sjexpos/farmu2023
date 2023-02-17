package com.farmu.interview.service.urlshortener.api.model.exception;

public class StateNotFoundException extends NotFoundException {
    public StateNotFoundException() {
    }

    public StateNotFoundException(String message) {
        super(message);
    }
}
