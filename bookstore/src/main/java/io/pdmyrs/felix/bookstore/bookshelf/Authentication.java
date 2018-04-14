package io.pdmyrs.felix.bookstore.services;

public interface Authentication
{
    String login(String username, char[] password) throws InvalidCredentialsException;

    void logout(String session);
}
