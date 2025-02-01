import java.util.Random;
import java.util.Scanner;

import static java.lang.Thread.sleep;

public class Duchess {
    private final ListManager listManager;
    private boolean wantsToPlayCN4 = false;

    public Duchess() {
        listManager = new ListManager();
    }

    public String getBotResponse(String userInput) {
        if (userInput.toLowerCase().startsWith("list ")) {
            String[] parts = userInput.split(" ", 4);

            if (parts.length == 2) {
                String name = parts[1].trim();
                if (!listManager.getListContent(name).contains("no list")) {
                    return listManager.getListContent(name);
                }
                listManager.addList(name);
                return "A new list, by the name of '" + name + "' has been created.";
            } else if (parts.length == 3) {
                return listManager.addItemToList(parts[1].trim(), parts[2].trim());
            } else if (parts.length == 4) {
                return listManager.toggleItem(parts[1].trim(), parts[2].trim(), parts[3].trim().equals("tick"));
            }
        }
        if (userInput.equalsIgnoreCase("connect")) {
            wantsToPlayCN4 = true;
            return "You want to play connect four? The game created by Howard Wexler, and first sold under the Connect Four trademark by Milton Bradley in February 1974?\nOkay!";
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

        String logo = """
                 ____              _ 
                |  _ \\ _   _  ____| |_    ___  ___  ___
                | | | | | | |/ ___|  _ \\ / _ \\/ __|/ __|
                | |_| | |_| | |___| | | |  __/\\__ \\\\__ \\
                |____/ \\__,_|\\____|_| |_|\\___||___/|___/
                """;
        for (int i = 0; i < 50 ; i++) {
            System.out.println();
        }
        Printer.PrintNicely(
                "NOTICE: RUN IN OS TERMINAL (cmd, powershell) FOR SMOOTHEST EXPERIENCE. I PROMISE ITS WAYYY COOLER!!!!\nOpen  out/production/ip  and run  java Duchess\n\nHello from\n"
                        + logo
                        + "\nHow may I serve you today?\n  ~Type -h for help~"
        , 3);

        Duchess duchess = new Duchess();

        while (true) {
            System.out.print("You: ");
            userInput = scanner.nextLine();

            if (userInput.equalsIgnoreCase("quit")) {
                Printer.PrintNicely("Goodbye! It was nice chatting.");
                break;
            } else if (userInput.equalsIgnoreCase("-h")) {
                Printer.PrintNicely(""" 
                        Here are some commands you can try:
                          -h: Display help
                          quit: Exit the chatbot
                          connect: Play a game of connect 4
                          list {name}: Start a list of name {name} if it doesn't exist, or display the list if it does
                          list {name} {item}: Add an item to an existing list
                          list {name} {item} tick: Tick an item in an existing list
                          list {name} {item} untick: Untick an item in an existing list
                        """);
                continue;
            }

            Printer.PrintNicely("1");
            Printer.PrintNicely("11111");
            Printer.PrintNicely("1111111111");
            Printer.PrintNicely("111111111111111");
            Printer.PrintNicely("11111111111111111111");
            Printer.PrintNicely("111111111111111111111111111");
            Printer.PrintNicely("111111111111111111111111111111111111111111111111");

            Printer.PrintNicely("Duchess: " + duchess.getBotResponse(userInput));



            if (duchess.wantsToPlayCN4) {
                Printer.PrintNicely("Loading.");
                for (int i = 0; i < 10; i++) {
                    System.out.print(".");
                    sleep(500);
                }
                boolean won = new ConnectFourGame().playGame();
                duchess.wantsToPlayCN4 = false;
                Printer.PrintNicely("Duchess: You " + (won ? "won! well played sire..." : "lost! sucks to suck..."));
            }
        }

        scanner.close();
    }
}
