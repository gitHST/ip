package duchess.exceptions;

/**
 * Exception thrown when the description of a todo is empty.
 */
public class EmptyTodoDescriptionException extends DuchessException {
    public EmptyTodoDescriptionException() {
        super("OOPS!!! The description of a todo cannot be empty.");
    }
}
