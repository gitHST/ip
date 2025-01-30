import java.util.Random;
import java.util.Scanner;

public class Duchess {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String userInput;

        String logo = " ____              _\n"
                + "|  _ \\ _   _  ____| |_    ___  ___  ___\n"
                + "| | | | | | |/ ___|  _ \\ / _ \\/ __|/ __|\n"
                + "| |_| | |_| | |___| | | |  __/\\__ \\\\__ \\\n"
                + "|____/ \\__,_|\\____|_| |_|\\___||___/|___/\n";
        System.out.println("\nHello from\n" + logo + "\nHow may I serve you today?\n  ~Type -h for help~");

        while (true) {
            System.out.print("You: ");
            userInput = scanner.nextLine();

            if (userInput.equalsIgnoreCase("bye")) {
                System.out.println("Goodbye! It was nice chatting.");
                break;
            }

            System.out.println("Duchess: " + getBotResponse(userInput));
        }

        scanner.close();
    }

    public static String getBotResponse(String userInput) {
        return userInput;
    }
}
