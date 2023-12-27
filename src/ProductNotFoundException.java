/**
 * ProductNotFoundException is a custom exception class that extends the standard Exception class.
 * It is used to represent custom exception scenarios in the application.
 */
public class ProductNotFoundException extends Exception {

    /**
     * Constructs a new ProductNotFoundException with the specified detail message.
     *
     * @param message the detail message (which is saved for later retrieval by the getMessage() method)
     */
    public ProductNotFoundException(String message) {
        super(message);
    }
}
