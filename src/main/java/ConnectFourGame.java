import java.util.Scanner;

public class ConnectFourGame {
    public boolean playGame() {
    clearConsole();
    System.out.println("Welcome to Connect 4!\nWhat size board would you like to play on? (e.g. 6x7)");
    Scanner scanner = new Scanner(System.in);
    String boardSize;
    int rows = 0, cols = 0;
    boolean validInput = false;

    while (!validInput) {
        boardSize = scanner.nextLine();
        String[] boardSizeArr = boardSize.split("x");
        if (boardSizeArr.length == 2) {
            try {
                rows = Integer.parseInt(boardSizeArr[0]);
                cols = Integer.parseInt(boardSizeArr[1]);
                if (rows > 0 && cols > 0) {
                    validInput = true;
                } else {
                    System.out.println("Hmm... very clever of you! I have to ask that you kindly enter positive integers for the board size.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Good sir, please enter the board size in the format 'rows x cols' (e.g. 6x7). Much appreciated!");
            }
        } else {
            System.out.println("Good sir, please enter the board size in the format 'rows x cols' (e.g. 6x7). Much appreciated!");
        }
    }

    return true;
}

    public String printBoard(char[][] board) {
        StringBuilder sb = new StringBuilder();
        for (char[] chars : board) {
            sb.append("|");
            for (char aChar : chars) {
                sb.append(aChar);
                sb.append("|");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public static void clearConsole() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }
}
