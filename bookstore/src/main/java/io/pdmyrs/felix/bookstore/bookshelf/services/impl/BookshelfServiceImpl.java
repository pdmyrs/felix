package io.pdmyrs.felix.bookstore.bookshelf.services.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import io.pdmyrs.felix.bookstore.bookshelf.BookAlreadyExistsException;
import io.pdmyrs.felix.bookstore.bookshelf.BookInventoryNotRegisteredRuntimeException;
import io.pdmyrs.felix.bookstore.bookshelf.BookNotFoundException;
import io.pdmyrs.felix.bookstore.bookshelf.InvalidBookException;
import io.pdmyrs.felix.bookstore.bookshelf.InvalidCredentialsException;
import io.pdmyrs.felix.bookstore.bookshelf.services.BookshelfService;
import io.pdmyrs.felix.bookstore.inventory.Book;
import io.pdmyrs.felix.bookstore.inventory.BookInventory;
import io.pdmyrs.felix.bookstore.inventory.MutableBook;


public class BookshelfServiceImpl implements BookshelfService
{
    String session;

    BundleContext context;

    public BookshelfServiceImpl(BundleContext context) {
        this.context = context;
    }

    private BookInventory lookupBookInventory() {
        @SuppressWarnings("unchecked")
		ServiceReference<BookInventory> ref = (ServiceReference<BookInventory>) this.context.getServiceReference(BookInventory.class.getName());
        if (ref == null) {
            throw new BookInventoryNotRegisteredRuntimeException(BookInventory.class.getName());
        }
        return (BookInventory) this.context.getService(ref);
    }

    public String login(String username, char[] password) throws InvalidCredentialsException {
        if ("admin".equals(username) && Arrays.equals(password, "admin".toCharArray())) {
            this.session = Long.toString(System.currentTimeMillis());
            return this.session;
        }
        throw new InvalidCredentialsException(username);
    }

    protected void checkSession(String session) {
        if (this.session == null || !this.session.equals(session)) {
            throw new SessionNotValidRuntimeException(session);
        }
    }

    public void logout(String session) {
        checkSession(session);
        this.session = null;
    }

    public Book getBook(String session, String isbn) throws BookNotFoundException {
        checkSession(session);
        BookInventory inv = lookupBookInventory();
        return inv.loadBook(isbn);
    }

    public MutableBook getBookForEdit(String session, String isbn) throws BookNotFoundException {
        checkSession(session);
        BookInventory inv = lookupBookInventory();
        return inv.loadBookForEdit(isbn);
    }

    public void addBook(String session, String isbn, String title, String author, String group,
                    int grade) throws BookAlreadyExistsException, InvalidBookException {
        checkSession(session);

        BookInventory inv = lookupBookInventory();

        MutableBook book = inv.createBook(isbn);
        book.setTitle(title);
        book.setAuthor(author);

        inv.storeBook(book);
    }

    public void removeBook(String session, String isbn) throws BookNotFoundException {
        checkSession(session);
        BookInventory inv = lookupBookInventory();
        inv.removeBook(isbn);
    }


	@Override
	public void addBook(String session, String isbn, String title, String author)
			throws BookAlreadyExistsException, InvalidBookException {
		// TODO Auto-generated method stub
		
	}



}
