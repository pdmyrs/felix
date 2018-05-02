package io.pdmyrs.felix.bookstore.inventory;

/** Holds information on a book in the bookshelf. */
public interface Book {

    /** Get the book reference number (ISBN) */
    String getIsbn();

    /** Get the book title */
    String getTitle();

    /** Get the book author */
    String getAuthor();

}
