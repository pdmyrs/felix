package io.pdmyrs.felix.bookstore.bookshelf.services;

import io.pdmyrs.felix.bookstore.bookshelf.Authentication;
import io.pdmyrs.felix.bookstore.bookshelf.BookAlreadyExistsException;
import io.pdmyrs.felix.bookstore.bookshelf.BookNotFoundException;
import io.pdmyrs.felix.bookstore.bookshelf.InvalidBookException;
import io.pdmyrs.felix.bookstore.inventory.Book;

public interface BookshelfService extends Authentication {
    void addBook(String session, String isbn, String title, String author)
                    throws BookAlreadyExistsException, InvalidBookException;
    void removeBook(String session, String isbn) throws BookNotFoundException;
    Book getBook(String session, String isbn) throws BookNotFoundException;

}
