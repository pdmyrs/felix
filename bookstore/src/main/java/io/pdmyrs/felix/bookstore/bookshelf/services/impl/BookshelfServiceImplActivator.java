package io.pdmyrs.felix.bookstore.bookshelf.services.impl;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;

import io.pdmyrs.felix.bookstore.bookshelf.BookAlreadyExistsException;
import io.pdmyrs.felix.bookstore.bookshelf.InvalidBookException;
import io.pdmyrs.felix.bookstore.bookshelf.InvalidCredentialsException;
import io.pdmyrs.felix.bookstore.bookshelf.services.BookshelfService;


public class BookshelfServiceImplActivator implements BundleActivator {
    ServiceRegistration reg = null;

    public void start(BundleContext context) throws Exception {
        this.reg = context.registerService(BookshelfService.class.getName(),
            new BookshelfServiceImpl(context), null);

        testService(context);
    }

    private void testService(BundleContext context) {
        // retrieve service
        ServiceReference ref = context.getServiceReference(BookshelfService.class.getName());
        if (ref==null) {
            throw new RuntimeException(
                "Service not registered: " + BookshelfService.class.getName());
        }
        BookshelfService service = (BookshelfService) context.getService(ref);
        // authenticate and get session
        String session;
        try {
            System.out.println("\nSigning in. . .");
            session = service.login("admin", "admin".toCharArray());
        }
        catch (InvalidCredentialsException e) {
            e.printStackTrace();
            return;
        }

        // add a few books
        try {
            System.out.println("\nAdding books. . .");
            service.addBook(session, "123-4567890100", "Book 1 Title", "John Doe");
            service.addBook(session, "123-4567890101", "Book 2 Title", "Will Smith");
            service.addBook(session, "123-4567890200", "Book 3 Title", "John Doe");
            service.addBook(session, "123-4567890201", "Book 4 Title", "Jane Doe");
        }
        catch (BookAlreadyExistsException e) {
            e.printStackTrace();
            return;
        }
        catch (InvalidBookException e) {
            e.printStackTrace();
            return;
        }

    }

    public void stop(BundleContext context) throws Exception {
        if (this.reg!=null) {
            context.ungetService(reg.getReference());
        }
    }

}
