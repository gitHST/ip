package cn4;

/**
 * Represents a Connect Four board with dynamic scaling and piece placement.
 */
public class ConnectFourBoard {
    private static final char EMPTY_SLOT = ' ';
    private static final int DEFAULT_SCALE = 2;

    private final int rows;
    private final int cols;
    private final char[][] board;
    private int scale;

    /**
     * Constructs a Connect Four board with the specified number of columns and rows.
     *
     * @param cols The number of columns.
     * @param rows The number of rows.
     */
    public ConnectFourBoard(int cols, int rows) {
        this.rows = rows;
        this.cols = cols;
        this.board = new char[rows][cols];
        initializeBoard();
        this.scale = DEFAULT_SCALE;
    }

    /**
     * Initializes the board by filling it with empty slots.
     */
    private void initializeBoard() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                board[i][j] = EMPTY_SLOT;
            }
        }
    }

    /**
     * Places a game piece in the specified column.
     *
     * @param column The column index (0-based).
     * @param piece  The piece to place (as a single-character string).
     * @return True if the piece was placed successfully, otherwise false.
     */
    public boolean placePiece(int column, String piece) {
        char pieceChar = piece.charAt(0);
        for (int i = rows - 1; i >= 0; i--) {
            if (board[i][column] == EMPTY_SLOT) {
                board[i][column] = pieceChar;
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if a player has won the game.
     * Currently unimplemented.
     *
     * @param piece The piece to check for a winning condition.
     * @return Always returns false (not yet implemented).
     */
    public boolean checkWin(String piece) {
        return false; // To be implemented
    }

    /**
     * Checks if the board is full, resulting in a draw.
     *
     * @return True if the board is full, otherwise false.
     */
    public boolean checkDraw() {
        for (int i = 0; i < cols; i++) {
            if (board[0][i] == EMPTY_SLOT) {
                return false;
            }
        }
        return true;
    }

    /**
     * Generates a string representation of the board for display.
     *
     * @return The formatted board as a string.
     */
    public String printBoard() {
        StringBuilder boardString = new StringBuilder();
        int cellWidth = (2 * scale) - 1;
        int rowHeight = (scale - 1) / 2 + 1;

        addCursorReset(boardString, rowHeight);
        addBoardTopBorder(boardString, cellWidth);
        addBoardRows(boardString, cellWidth, rowHeight);
        addColumnLabels(boardString, cellWidth);

        return boardString.toString();
    }

    /**
     * Moves the cursor up to overwrite the previous board printout.
     */
    private void addCursorReset(StringBuilder boardString, int rowHeight) {
        int totalLines = (rowHeight * rows) + ((rowHeight > 1) ? rows + 1 : 0) + 2;
        if (totalLines > 0) {
            boardString.append("\r").append("\033[").append(totalLines).append("A");
        }
    }

    /**
     * Adds the top border of the board.
     */
    private void addBoardTopBorder(StringBuilder boardString, int cellWidth) {
        if (scale > 1) {
            boardString.append("-".repeat(cols * (cellWidth + 1) + 1)).append("\n");
        }
    }

    /**
     * Adds all the rows of the board.
     */
    private void addBoardRows(StringBuilder boardString, int cellWidth, int rowHeight) {
        for (int i = 0; i < rows; i++) {
            for (int k = 0; k < rowHeight; k++) {
                boardString.append("|");
                for (int j = 0; j < cols; j++) {
                    boardString.append(String.valueOf(board[i][j]).repeat(cellWidth)).append("|");
                }
                boardString.append("\n");
            }
            if (scale > 1) {
                boardString.append("-".repeat(cols * (cellWidth + 1) + 1)).append("\n");
            }
        }
    }

    /**
     * Adds column index labels at the bottom of the board.
     */
    private void addColumnLabels(StringBuilder boardString, int cellWidth) {
        for (int i = 0; i < cols; i++) {
            int padding = (cellWidth - 1) / 2;
            boardString.append(" ".repeat(padding + 1)).append(i)
                    .append(" ".repeat(cellWidth - 1 - padding));
        }
    }

    /**
     * Gets the current board scaling factor.
     *
     * @return The scale factor.
     */
    public int getScale() {
        return scale;
    }

    /**
     * Gets the number of rows in the board.
     *
     * @return The number of rows.
     */
    public int getRows() {
        return rows;
    }

    /**
     * Gets the number of columns in the board.
     *
     * @return The number of columns.
     */
    public int getCols() {
        return cols;
    }

    /**
     * Sets the scale for board rendering.
     *
     * @param scale The new scale factor.
     */
    public void setScale(int scale) {
        this.scale = scale;
    }
}
