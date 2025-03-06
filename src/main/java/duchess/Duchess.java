package duchess;

import duchess.cn4.ConnectFourGame;
import duchess.exceptions.DuchessException;
import duchess.exceptions.UnrecognizedCommandException;
import duchess.list.ListManager;

import java.util.Random;
import java.util.Scanner;

import static java.lang.Thread.sleep;

/**
 * Duchess chatbot class that provides a simple interactive chatbot.
 */
public class Duchess {
    private static final boolean IS_USING_TERMINAL = System.console() != null;
    private static final String HELP_MESSAGE = """
            Here are some commands you can try:
              -h:         Display help
              quit:       Exit the chatbot
              connect4:   Play a game of connect 4
              Lists:
                Please use _ for spaces!
                list {name}: Start or display a list named {name}
                list {name} {item}: Add an item to an existing list
                list {name} {item} delete: Remove an item from an existing list
                list {name} {item} tick: Tick an item in an existing list
                list {name} {item} untick: Untick an item in an existing list
                list {name} todo: Add a todo item
                list {name} deadline {item} by {deadline}: Add a deadline item
                list {name} event {item} from {from} to {to}: Add an event item
            """;
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

    private final ListManager listManager;
    private boolean wantsToPlayCN4 = false;

    /**
     * Constructs a Duchess chatbot instance.
     */
    public Duchess() {
        this.listManager = new ListManager();
    }

    /**
     * Processes user input and returns a bot response.
     *
     * @param userInput The input string from the user.
     * @return A response string from the bot.
     * @throws DuchessException If an error occurs while processing the input.
     */
    public String getBotResponse(String userInput) throws DuchessException {
        String lowerCaseInput = userInput.toLowerCase();

        if (lowerCaseInput.startsWith("list ")) {
            try {
                return listManager.handleListCommand(userInput);
            } catch (Exception e) {
                throw new DuchessException("An error occurred while processing your list command.");
            }
        }

        if (lowerCaseInput.equals("connect4")) {
            return handleConnectFourRequest();
        }

        throw new UnrecognizedCommandException();
    }

    /**
     * Handles the user request to play Connect Four.
     *
     * @return Response message indicating if the game can be played.
     */
    private String handleConnectFourRequest() {
        if (IS_USING_TERMINAL) {
            wantsToPlayCN4 = true;
            return "You want to play connect four? Okay!\n\n";
        }
        return "I'm sorry dear, we simply can't play connect four in this environment! :( " +
                "Please be a doll and run me in a terminal to play connect four.";
    }

    /**
     * Returns a random invalid command response.
     *
     * @return A random response indicating unrecognized input.
     */
    private String getRandomInvalidResponse() {
        Random random = new Random();
        int index = random.nextInt(INVALID_COMMAND_RESPONSES.length);
        return INVALID_COMMAND_RESPONSES[index] + "\n  ~Type -h for help~";
    }

    /**
     * The main entry point for running the chatbot.
     *
     * @param args Command-line arguments.
     * @throws InterruptedException If thread sleep is interrupted.
     */
    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        Duchess duchess = new Duchess();
        clearScreen();
        promptTerminalWarning(scanner);
        printWelcomeMessage();

        while (true) {
            System.out.print("You: ");
            String userInput = scanner.nextLine();

            if (userInput.equalsIgnoreCase("quit")) {
                Printer.printNicely("Goodbye! It was nice chatting.");
                break;
            } else if (userInput.equalsIgnoreCase("-h")) {
                Printer.printNicely(HELP_MESSAGE, 1.5);
                continue;
            }

            try {
                Printer.printNicely("Duchess: " + duchess.getBotResponse(userInput));
            } catch (DuchessException e) {
                Printer.printNicely("Duchess: " + e.getMessage());
            }

            if (duchess.wantsToPlayCN4) {
                launchConnectFour(duchess);
            }
        }
        scanner.close();
    }

    /**
     * Clears the console screen.
     */
    private static void clearScreen() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }

    /**
     * Prompts the user if they are running the program outside a terminal.
     *
     * @param scanner The scanner to read user input.
     */
    private static void promptTerminalWarning(Scanner scanner) {
        if (!IS_USING_TERMINAL) {
            System.out.println("""
    It seems you are running this program in an IDE...
    Please consider running it in windows terminal for the best experience.""");
        }
    }

    /**
     * Prints the welcome message and ASCII logo.
     */
    private static void printWelcomeMessage() throws InterruptedException {
        String logo = """
                 ____              _                          
                |  _ \\ _   _  ____| |_    ___  ___  ___      
                | | | | | | |/ ___|  _ \\ / _ \\/ __|/ __|     
                | |_| | |_| | |___| | | |  __/\\__ \\\\__ \\     
                |____/ \\__,_|\\____|_| |_|\\___||___/|___/     
                """;
        Printer.printNicely("Greetings from the almighty:", 0.3);
        sleep(300);
        Printer.printLogo(5, logo, 20);
        Printer.printNicely("\nHow may I serve you today?\n  ~Type -h for help~\n", 3);
    }

    /**
     * Launches the Connect Four game.
     *
     * @param duchess The chatbot instance.
     * @throws InterruptedException If thread sleep is interrupted.
     */
    private static void launchConnectFour(Duchess duchess) throws InterruptedException {
        Printer.printNicely("Loading");
        for (int i = 0; i < 6; i++) {
            System.out.print(".");
            sleep(500);
        }
        boolean won = new ConnectFourGame().playGame();
        duchess.wantsToPlayCN4 = false;
        Printer.printNicely("Duchess: " + (won ? "You won!" : "Oh no, you lost!") + "\n  ~Type -h for help~");
    }
}
