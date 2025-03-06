package duchess.exceptions;

import java.util.Random;

/**
 * Exception thrown when the user enters an unrecognized command.
 * A random message is selected from a predefined set of responses
 * to provide feedback to the user.
 */
public class UnrecognizedCommandException extends DuchessException {
    private static final String[] INVALID_COMMAND_RESPONSES = {
            "I'm sorry dear, I didn't quite catch that...",
            "You'll have to speak up darling, I'm a little hard of hearing...",
            "Sorry, I must say I don't quite understand...",
            "Oh my, did you say something? I didn't quite hear you right...",
            "Pardon me dear, but I didn’t hear a word of that...",
            "Oh heavens, I'm afraid that doesn't quite ring a bell...",
            "Dear me, I do believe you’ll have to repeat that...",
            "I do beg your pardon, but I’m not sure what you mean...",
            "Now, now, darling, you’ve lost me with that one..."
    };

    /**
     * Selects a random response from the list of invalid command responses.
     *
     * @return A randomly selected response indicating an unrecognized command.
     */
    private static String getRandomInvalidResponse() {
        Random random = new Random();
        int index = random.nextInt(INVALID_COMMAND_RESPONSES.length);
        return INVALID_COMMAND_RESPONSES[index] + "\n  ~Type -h for help~";
    }

    /**
     * Constructs a new {@code UnrecognizedCommandException} with a randomly
     * chosen response from {@code INVALID_COMMAND_RESPONSES}.
     */
    public UnrecognizedCommandException() {
        super(getRandomInvalidResponse());
    }
}
