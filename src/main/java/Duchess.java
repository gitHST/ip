import java.util.Random;
import java.util.Scanner;

import static java.lang.Thread.sleep;

public class Duchess {
    private final ListManager listManager;
    private boolean wantsToPlayCN4 = false;
    private static final boolean isUsingTerminal = System.console() != null;

    public Duchess() {
        listManager = new ListManager();
    }

    public String getBotResponse(String userInput) {
        if (userInput.toLowerCase().startsWith("list ")) {
            return listManager.handleListCommand(userInput);
        }

        if (userInput.equalsIgnoreCase("connect4")) {
            if (isUsingTerminal) {
                wantsToPlayCN4 = true;
                return "You want to play connect four? The game created by Howard Wexler, and first sold under the Connect Four trademark by Milton Bradley in February 1974?\nOkay!\n\n";
            } else {
                return "I'm sorry dear, we simply can't play connect four in this environment! :( Please be a doll and run me in a terminal to play connect four.";
            }
        }
        return getRandomInvalidResponse();
    }

    private String getRandomInvalidResponse() {
        String[] invalidCommandResponses = {
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
        Random random = new Random();
        int randomIndex = random.nextInt(invalidCommandResponses.length);
        return invalidCommandResponses[randomIndex] + "\n  ~Type -h for help~";
    }

    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        String userInput;
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }

        if (!isUsingTerminal) {
            System.out.println("""
                    It seems you are running this program in an IDE, I would really appreciate it if you could run it in Windows Terminal in order to enjoy the nice animations I created
                    (They look really cool!)
                    
                    INSTRUCTIONS:
                      - Open directory "out/production/ip" in a Windows Terminal and type "java Duchess"
                      - Windows Terminal - https://apps.microsoft.com/detail/9n0dx20hk701?hl=en-US&gl=US
                      - If you're on Mac or Linux, you can use iTerm2 or any other terminal that supports ANSI escape codes
                      
                    If you decide to proceed without, ALL marked tasks will still function perfectly fine.
                    
                    Press enter to continue and accept the inferior experience of IDE printing limitations...
                    
                    """);
            scanner.nextLine();
        }

        String logo = """
                 ____              _                          
                |  _ \\ _   _  ____| |_    ___  ___  ___      
                | | | | | | |/ ___|  _ \\ / _ \\/ __|/ __|     
                | |_| | |_| | |___| | | |  __/\\__ \\\\__ \\     
                |____/ \\__,_|\\____|_| |_|\\___||___/|___/     
                """;
        System.out.print("\033[" + (logo.split("\n").length + 1) + "B");
        System.out.print("\033[" + (logo.split("\n").length + 1) + "A");

        Printer.printNicely("Greetings from the almighty:", 0.3);
        sleep(300);

        Printer.printLogo(5, logo, 20);
        Printer.printNicely("\nHow may I serve you today?\n  ~Type -h for help~\n", 3);

        Duchess duchess = new Duchess();

        while (true) {
            Printer.printNicely("You: ");
            userInput = scanner.nextLine();

            if (userInput.equalsIgnoreCase("quit")) {
                Printer.printNicely("Goodbye! It was nice chatting.");
                break;
            } else if (userInput.equalsIgnoreCase("-h")) {
                Printer.printNicely(""" 
                        Here are some commands you can try:
                        
                          -h:         Display help
                          quit:       Exit the chatbot
                          connect4:   Play a game of connect 4
                          Lists:
                            Please use _ for spaces! You know whitespaces flare up my allergies!
                            list {name}: Start a list of name {name} if it doesn't exist, or display the list if it does
                            list {name} {item}: Add an item to an existing list
                            list {name} {item} tick: Tick an item in an existing list
                            list {name} {item} untick: Untick an item in an existing list
                            list {name} {item} todo: Add a todo item to an existing list
                            list {name} deadline {item} by {deadline}: Add a deadline item to an existing list
                            list {name} event {item} from {from} to {to}: Add an event item to an existing list
                        """, 1.5);
                continue;
            }

            Printer.printNicely("Duchess: " + duchess.getBotResponse(userInput));

            if (duchess.wantsToPlayCN4) {
                Printer.printNicely("Loading");
                for (int i = 0; i < 6; i++) {
                    System.out.print(".");
                    sleep(500);
                }
                boolean won = new ConnectFourGame().playGame();
                duchess.wantsToPlayCN4 = false;
                Printer.printNicely("Duchess: You " + (won ? "won! well played sire..." : "lost! sucks to suck..."));
            }
        }

        scanner.close();
    }
}
