package io.pdmyrs.felix.bookstore.services;

public class InvalidCredentialsException extends Exception
{
    private static final long serialVersionUID = 7194217072302133529L;

    public InvalidCredentialsException(String username) {
        super("Invalid credentials for " + username);
    }
}
