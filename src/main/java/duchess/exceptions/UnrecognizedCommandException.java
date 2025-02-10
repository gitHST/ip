package duchess.exceptions;

/**
 * Exception thrown when the user enters an unrecognized command.
 */
public class UnrecognizedCommandException extends DuchessException {
    public UnrecognizedCommandException() {
        super("OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}
