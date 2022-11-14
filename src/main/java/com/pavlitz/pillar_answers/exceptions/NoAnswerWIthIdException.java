package com.pavlitz.pillar_answers.exceptions;

public class NoAnswerWIthIdException extends NullPointerException {
    private static final String message = "There is no answer with id ";

    public NoAnswerWIthIdException(long id) {
        super(message + id);
    }
}
