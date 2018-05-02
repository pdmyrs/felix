package io.pdmyrs.felix.bookstore.bookshelf;

public class BookInventoryNotRegisteredRuntimeException extends RuntimeException
{
    private static final long serialVersionUID = 2712890714045120257L;

    public BookInventoryNotRegisteredRuntimeException(String className) {
        super("BookInventory not registered, looking under: " + className);
    }
}
