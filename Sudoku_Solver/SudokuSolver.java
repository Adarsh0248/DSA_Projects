import java.util.Arrays;

public class SudokuSolver {
    // Global variable to store the solved board
    static char[][] ans = new char[9][9];

    // Check whether the board is currently valid (no duplicates in rows, columns, or 3x3 boxes)
    public static boolean isBoardValid(char[][] board) {
        for (int i = 0; i < 9; i++) {
            boolean[] rowSeen = new boolean[9];
            boolean[] colSeen = new boolean[9];

            for (int j = 0; j < 9; j++) {
                // Check each row
                if (board[i][j] != '.') {
                    int num = board[i][j] - '1';
                    if (rowSeen[num]) return false;
                    rowSeen[num] = true;
                }

                // Check each column
                if (board[j][i] != '.') {
                    int num = board[j][i] - '1';
                    if (colSeen[num]) return false;
                    colSeen[num] = true;
                }
            }
        }

        // Check all 3x3 subgrids
        for (int boxRow = 0; boxRow < 9; boxRow += 3) {
            for (int boxCol = 0; boxCol < 9; boxCol += 3) {
                boolean[] boxSeen = new boolean[9];
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        char current = board[boxRow + i][boxCol + j];
                        if (current != '.') {
                            int num = current - '1';
                            if (boxSeen[num]) return false;
                            boxSeen[num] = true;
                        }
                    }
                }
            }
        }

        return true; // Board is valid
    }

    // Recursive backtracking function to solve the Sudoku
    public static void sodukuSolver(char[][] board, int row, int col) {
        // Check if the initial board is valid
        if (!isBoardValid(board)) {
            System.out.println("The entered board is Invalid");
            return;
        }

        // If we've reached beyond the last row, store and display the result
        if (row >= board.length) {
//            for (int i = 0; i < board.length; i++) {
//                for (int j = 0; j < board[i].length; j++) {
//                    ans[i][j] = board[i][j];
//                }
//            }
            display(board);
            System.out.println();
            return;
        }

        // Move to next row if current row is complete
        if (col == board[0].length) {
            for (int i = 0; i < col; i++) {
                if (board[row][i] == '.') return;
            }
            sodukuSolver(board, row + 1, 0);
            return;
        }

        // Try all valid numbers if current cell is empty
        if (board[row][col] == '.') {
            for (int i = 1; i < 10; i++) {
                // Skip if not safe
                if (i == 9 && !isSafe(board, row, col, i)) return;

                if (isSafe(board, row, col, i)) {
                    board[row][col] = (char) (i + '0');  // Place number
                    sodukuSolver(board, row, col + 1);   // Recur

                    board[row][col] = '.'; // Backtrack
                }
            }
        }

        // Move to next cell
        sodukuSolver(board, row, col + 1);
    }

    // Check if placing number i at board[row][col] is safe
    public static boolean isSafe(char[][] board, int row, int col, int i) {
        if (board[row][col] != '.') return false;

        // Check column
        for (int x = 0; x < board.length; x++) {
            if (board[x][col] == (char)(i + '0')) return false;
        }

        // Check row
        for (int x = 0; x < board[row].length; x++) {
            if (board[row][x] == (char)(i + '0')) return false;
        }

        // Determine top-left cell of the 3x3 subgrid
        int rowStart = (row / 3) * 3;
        int colStart = (col / 3) * 3;

        // Check 3x3 subgrid
        for (int k = rowStart; k < rowStart + 3; k++) {
            for (int j = colStart; j < colStart + 3; j++) {
                if (board[k][j] == (char)(i + '0')) return false;
            }
        }

        return true; // Placement is safe
    }

    // Print the board to the console
    private static void display(char[][] board) {
        for (char[] row : board) {
            System.out.println(Arrays.toString(row));
        }
    }

    public static void main(String[] args) {
        char[][] board = {
                {'5','3','.','.','7','.','.','.','.'},
                {'6','.','.','1','9','5','.','.','.'},
                {'.','9','8','.','.','.','.','6','.'},
                {'8','.','.','.','6','.','.','.','3'},
                {'4','.','.','8','.','3','.','.','1'},
                {'7','.','.','.','2','.','.','.','6'},
                {'.','6','.','.','.','.','2','8','.'},
                {'.','.','.','4','1','9','.','.','5'},
                {'.','.','.','.','8','.','.','7','9'}
        };

        sodukuSolver(board, 0, 0);
    }
}
