package io.pdmyrs.felix.bookstore.bookshelf.services.impl;

public class SessionNotValidRuntimeException extends RuntimeException
{
    private static final long serialVersionUID = 7465520406478405271L;

    public SessionNotValidRuntimeException(String session) {
        super("Session not valid ("+session+"), or session expired; you must login.");
    }
}
