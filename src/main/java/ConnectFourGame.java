import java.util.Scanner;

public class ConnectFourGame {
    private boolean gameEnded = false;
    private ConnectFourBoard board;
    public boolean playGame() throws InterruptedException {
        System.out.println("\n".repeat(50));
        Printer.printNicely("""
                Welcome to Connect 4!
                
                Controls:
                 - {integer}, column you place your piece
                 - scale {1-5}, scale of the board
                
                You are:           @
                Your opponent is:  #
                
                What size board would you like to play on? (e.g. 6x7)""");
        int[] boardSize = askBoardSize();
        board = new ConnectFourBoard(boardSize[0], boardSize[1]);
        Printer.printNicely(board.printBoard());

        while (!gameEnded) {
            playTurn();
        }

        return true;
    }

    public void playTurn() throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        int scale = -1;
        boolean validInput = false;

        String input = null;
        while (!validInput) {
            input = scanner.nextLine();
            if (input.contains("scale")) {
                try {
                    String scaleInput = input.replaceAll("\\s", "");
                    scale = Integer.parseInt(scaleInput.substring(5));
                    if (scale >= 1 && scale <= 5) {
                        validInput = true;
                    } else {
                        Printer.printNicely("Good sir, please enter the scale as an integer between 1 and 5. Much appreciated!");
                    }
                } catch (NumberFormatException e) {
                    Printer.printNicely("Good sir, please enter the scale as an integer between 1 and 5. Much appreciated!");
                } catch (IndexOutOfBoundsException e) {
                    Printer.printNicely("Good sir, please provide a valid scale number after 'scale'. Much appreciated!");
                }
            } else {
                try {
                    int col = Integer.parseInt(input);
                    if (col >= 0 && col < board.getCols()) {
                        validInput = true;
                    } else {
                        Printer.printNicely("Hmm... very clever of you! I have to ask that you kindly enter a column number within the board size.");
                    }
                } catch (NumberFormatException | InterruptedException e) {
                    Printer.printNicely("Good sir, please enter the column number as an integer. Much appreciated!");
                }
            }
        }
        if (scale != -1) {
            // Rescale the board
        } else {
            // Place the piece
        }
    }


    public int[] askBoardSize() {
        Scanner scanner = new Scanner(System.in);
        int rows = 0, cols = 0;
        boolean validInput = false;

        while (!validInput) {
            String boardSize = scanner.nextLine();
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
        return new int[]{rows, cols};
    }

    public static void main(String[] args) throws InterruptedException {
        while (true) {
            ConnectFourGame game = new ConnectFourGame();
            game.playGame();
        }
    }
}

