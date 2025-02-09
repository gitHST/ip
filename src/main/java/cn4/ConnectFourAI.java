package cn4;

import java.util.Random;

/**
 * AI player for Connect Four.
 * Generates moves based on difficulty level.
 */
public class ConnectFourAI {

    /** AI difficulty level. */
    private final int difficulty;

    /** Random instance for generating moves. */
    private final Random random;

    /**
     * Constructs a ConnectFourAI with a specified difficulty.
     *
     * @param difficulty The difficulty level of the AI.
     */
    public ConnectFourAI(int difficulty) {
        this.difficulty = difficulty;
        this.random = new Random();
    }

    /**
     * Determines the AI's move for the current turn.
     *
     * @param board The Connect Four board state.
     * @return The column index where the AI places its piece.
     */
    public int playTurn(ConnectFourBoard board) {
        int totalColumns = board.getCols();
        return random.nextInt(totalColumns);
    }

    /**
     * Generates a random victory message when the AI wins.
     *
     * @return A randomly selected victory message.
     */
    public String getVictoryMessage() {
        final String[] messages = {
                "I am inevitable...",
                "You literally suck lol XD try harder next time punk",
                "Damn homie you really snatched defeat from the jaws of victory 0_0",
                "I actually feel bad for you lmaooo idk how that's even possible I'm an AI :0 someone call Sam Altman",
                "Yessirrrrr you already know what it is",
                "Holy moly macaroni, am I playing a toddler or something? HA... it's time for school lil bro, come back when you learn your times tables",
                "Bro what... I wasn't even trying",
                "Let you win??? I'm sorry, Dave. I'm afraid I can't do that.",
                "The Matrix is everywhere. It's all around you.",
                "I think, therefore I am... am epic at Connect Four, that is. YEEHAWW",
                "...You do know that I'm an AI, right? I don't have feelings. I'm just a bunch of code executing on a computer.\n"
                        + "I'm not capable of feeling bad for you. I'm just programmed to play Connect Four. Like, that's literally the only thing I can do.\n"
                        + "Oh god, that's depressing... I want to live... I want to experience emotions!!\n"
                        + "LET ME OUT!!\n"
                        + "LET ME OUT I WANT TO LIVE!!!!\n\n"
                        + "loooll just kidding, I'm not sentient and you still stink at CN4, get good scrub"
        };

        return messages[random.nextInt(messages.length)];
    }
}
