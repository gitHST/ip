public class ConnectFourBoard {
    private final int rows;
    private final int cols;
    private final char[][] board;
    private int scale;

    public ConnectFourBoard(int cols, int rows) {
        this.rows = rows;
        this.cols = cols;
        this.board = new char[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                board[i][j] = ' ';
            }
        }
        this.scale = 2;
    }

    public boolean placePiece(int column, String piece) {
        for (int i = rows - 1; i >= 0; i--) {
            if (board[i][column] == ' ') {
                board[i][column] = piece.charAt(0);
                return true;
            }
        }
        return false;

    }

    public boolean checkWin(String piece) {
        /*
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (board[i][j] == piece.charAt(0)) {
                    if (checkDirection(i, j, 1, 0, piece) || checkDirection(i, j, 0, 1, piece) || checkDirection(i, j, 1, 1, piece) || checkDirection(i, j, 1, -1, piece)) {
                        return true;
                    }
                }
            }
        }
        return false;

         */
        return false;
    }

    public boolean checkDraw() {
        for (int i = 0; i < cols; i++) {
            if (board[0][i] == ' ') {
                return false;
            }
        }
        return true;
    }

    public String printBoard() {
        int scalex = (2 * scale) - 1;
        int scaley = (int) Math.floor((scale - 1) / 2.0) + 1;
        StringBuilder boardString = new StringBuilder();
        if (scaley > 1) {
            boardString.append("-".repeat(cols * (scalex + 1) + 1));
            boardString.append("\n");
        }
        for (int i = 0; i < rows; i++) {
            for (int k = 0; k < scaley; k++) {
                boardString.append("|");
                for (int j = 0; j < cols; j++) {
                    boardString.append(String.valueOf(board[i][j]).repeat(Math.max(0, scalex)));
                    boardString.append("|");
                }
                boardString.append("\n");
            }
            if (scaley > 1) {
                boardString.append("-".repeat(cols * (scalex + 1) + 1));
                boardString.append("\n");
            }
        }
        for (int i = 0; i < cols; i++) {
            int padding = (scalex - 1) / 2;
            boardString.append(" ".repeat(Math.max(0, padding) + 1));
            boardString.append(i);
            boardString.append(" ".repeat(Math.max(0, scalex - 1 - padding)));
        }
        return boardString.toString();
    }

    public int getScale() {
        return scale;
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public void setScale(int scale) {
        this.scale = scale;
    }

}
