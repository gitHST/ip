import java.util.Random;
import java.util.Scanner;
import java.util.HashMap;

public class Duchess {

    private HashMap<String, StringList> lists;

    public Duchess() {
        lists = new HashMap<>();
    }

    public void addList(String name, StringList stringList) {
        lists.put(name, stringList);
    }

    public String getListContent(String name) {
        StringList list = lists.get(name);
        if (list != null) {
            return list.toString();
        } else {
            return "I’m afraid I have no list under that name, darling.";
        }
    }

    public String getBotResponse(String userInput) {
        if (userInput.toLowerCase().startsWith("list ")) {
            String[] parts = userInput.split(" ", 3);

            if (parts.length == 2) {
                String name = parts[1].trim();
                if (!lists.containsKey(name)) {
                    addList(name, new StringList());
                    return "A new list, by the name of '" + name + "' has been created.";
                } else {
                    return getListContent(name);
                }
            } else if (parts.length == 3) {
                String name = parts[1].trim();
                String item = parts[2].trim();

                if (lists.containsKey(name)) {
                    lists.put(name, lists.get(name).addItem(item));
                    return "Added '" + item + "' to list '" + name + "'.";
                } else {
                    return "I’m afraid I have no list under that name, darling.";
                }
            } else {
                return "Please provide a valid command. Use 'list <name>' to view or create a list.";
            }
        } else {
            return getRandomInvalidResponse();
        }
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

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String userInput;

        String logo = """
                 ____              _ 
                |  _ \\ _   _  ____| |_    ___  ___  ___
                | | | | | | |/ ___|  _ \\ / _ \\/ __|/ __|
                | |_| | |_| | |___| | | |  __/\\__ \\\\__ \\
                |____/ \\__,_|\\____|_| |_|\\___||___/|___/
                """;
        System.out.println("\nHello from\n" + logo + "\nHow may I serve you today?\n  ~Type -h for help~");

        Duchess duchess = new Duchess();

        while (true) {
            System.out.print("You: ");
            userInput = scanner.nextLine();

            if (userInput.equalsIgnoreCase("quit")) {
                System.out.println("Goodbye! It was nice chatting.");
                break;
            } else if (userInput.equalsIgnoreCase("-h")) {
                System.out.println(""" 
                        Here are some commands you can try:
                          -h: Display help
                          quit: Exit the chatbot
                          list {name}: Start a list of name {name}
                          list {name} {item}: Add an item to an existing list
                        """);
                continue;
            }

            System.out.println("Duchess: " + duchess.getBotResponse(userInput));
        }

        scanner.close();
    }
}
