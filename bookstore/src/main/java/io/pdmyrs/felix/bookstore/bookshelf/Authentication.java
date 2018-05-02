package io.pdmyrs.felix.bookstore.bookshelf;

public interface Authentication
{
    String login(String username, char[] password) throws InvalidCredentialsException;

    void logout(String session);
}
