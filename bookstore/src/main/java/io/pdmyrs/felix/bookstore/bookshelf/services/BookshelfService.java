package io.pdmyrs.felix.bookstore.services;


public interface BookshelfService extends Authentication {
    void addBook(String session, String isbn, String title, String author)
                    throws BookAlreadyExistsException, InvalidBookException;
    void removeBook(String session, String isbn) throws BookNotFoundException;
    Book getBook(String session, String isbn) throws BookNotFoundException;

}
