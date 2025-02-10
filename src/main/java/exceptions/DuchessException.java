package exceptions;

/**
 * Custom exception class to represent errors specific to Duchess chatbot.
 */
public class DuchessException extends Exception {
    public DuchessException(String message) {
        super(message);
    }
}

