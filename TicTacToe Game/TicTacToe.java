
import java.util.*;

public class TicTacToe {
    private static String gamePvP(char[][] board, String first, String second ,Scanner sc){
        boolean gameOver = false;
        char currentMove = 'X';
        String currentPlayer = first;
        while(!gameOver) {
            display(board);
            System.out.print(currentPlayer+" where do you want to place "+ currentMove+" : ");
            int row = sc.nextInt();
            int col = sc.nextInt();
            if(board[row][col]==' '){
                board[row][col] = currentMove;
                int result = gameOver(board,currentMove);
                if(result==1){
                    display(board);
                    System.out.println(currentPlayer + " ( "+ currentMove+" ) wins the Game");
                    return currentPlayer;
                }else if(result == 0){
                    display(board);
                    System.out.println(" Its a Draw");
                    return "";
                } else{
                    currentPlayer = currentPlayer.equals(first) ? second:first;
                    currentMove = currentMove=='X' ? 'O':'X';
                }
            } else{
                System.out.println("Invalid Move, Try Again ");
                continue;
            }
        }
        return "";
    }

    private static void display(char[][] board) {
        for(char[] row: board){
            System.out.println(Arrays.toString(row));
        }
    }

    private static int gameOver(char[][] board, char ch) {
        int size = board.length;

        // Check rows and columns
        for (int i = 0; i < size; i++) {
            boolean rowWin = true;
            boolean colWin = true;
            for (int j = 0; j < size; j++) {
                if (board[i][j] != ch) rowWin = false;
                if (board[j][i] != ch) colWin = false;
            }
            if (rowWin || colWin) return 1;
        }

        // Check diagonals
        boolean mainDiagonal = true;
        boolean antiDiagonal = true;
        for (int i = 0; i < size; i++) {
            if (board[i][i] != ch) mainDiagonal = false;
            if (board[i][size - 1 - i] != ch) antiDiagonal = false;
        }
        if (mainDiagonal || antiDiagonal) return 1;

        // Check for draw (no empty cells)
        boolean boardFull = true;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j] != 'X' && board[i][j] != 'O') {
                    boardFull = false;
                    break;
                }
            }
        }

        return boardFull ? 0 : -1; // 0: draw, -1: game still on
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random random = new Random();
        char[][] board = {
                {' ',' ',' '},
                {' ',' ',' '},
                {' ',' ',' '}
        };
        boolean playOn = true;
        boolean gameOver = false;
        System.out.println("WELCOME TO TICK TAC TOE");
        while(playOn){
            System.out.print("Start New Game (Y/N) : ");
            String newGame = sc.next();
            System.out.println();
            if(newGame.equals("Y")) playOn=true;
            else if(newGame.equals("N")) {
                playOn=false;
                System.out.println("Thanks for Playing");
                break;
            } else{
                System.out.println("Invalid Response");
                continue;
            }

            while(playOn){
                System.out.println("Select mode : ");
                System.out.println("1. PvP");
                System.out.println("2. vs Computer (I never lose)");
                System.out.println("3. Exit ");
                System.out.print("Enter your mode (1/2/3): ");
                int mode = sc.nextInt();
                System.out.println();
                if(mode ==1){
                    System.out.print("Enter player1 name : ");
                    String player1 = sc.next();
                    System.out.println();
                    System.out.print("Enter player2 name: ");
                    String player2 = sc.next();
                    System.out.println();
                    int score1 = 0;
                    int score2 = 0;
                    boolean playAgain = true;
                    while(playAgain) {
                        System.out.print(player1 + ", choose Heads or Tails (H/T): ");
                        char choice = sc.next().toUpperCase().charAt(0);

                        // Toss simulation
                        char tossResult = random.nextBoolean() ? 'H' : 'T';
                        System.out.println("Toss result: " + (tossResult == 'H' ? "Heads" : "Tails"));

                        // Decide who goes first
                        if (choice == tossResult) {
                            System.out.println(player1 + " wins the toss and goes first!");
                            // Player 1 = 'X', Player 2 = 'O'
                            String result = gamePvP(board, player1, player2, sc);
                            if (result.equals(player1)) score1++;
                            else if (result.equals(player2)) score2++;
                            System.out.println("Current Standings : ");
                            System.out.println(player1 + " : " + score1);
                            System.out.println(player2 + " : " + score2);
                            System.out.print("Play Again (Y/N) : ");
                            char temp = sc.next().toUpperCase().charAt(0);
                            playAgain = temp=='Y';
                            resetBoard(board);
                        } else {
                            System.out.println(player2 + "wins the toss and goes first!");
                            // Player 2 = 'X', Player 1 = 'O'
                            String result = gamePvP(board, player2, player1, sc);
                            if (result.equals(player1)) score1++;
                            else if (result.equals(player2)) score2++;
                            System.out.println("Current Standings : ");
                            System.out.println(player1 + " : " + score1);
                            System.out.println(player2 + " : " + score2);
                            System.out.print("Play Again (Y/N) : ");
                            char temp = sc.next().toUpperCase().charAt(0);
                            playAgain = temp=='Y';
                            resetBoard(board);
                        }


                    }

                } else if(mode == 2){
                    System.out.print("Enter your name : ");
                    String player = sc.next();
                    String player2 = "Computer";
                    int score1 = 0;
                    int score2 = 0;
                    boolean playAgain = true;
                    while(playAgain) {
                        System.out.print(player + ", choose Heads or Tails (H/T): ");
                        char choice = sc.next().toUpperCase().charAt(0);

                        // Toss simulation
                        char tossResult = random.nextBoolean() ? 'H' : 'T';
                        System.out.println("Toss result: " + (tossResult == 'H' ? "Heads" : "Tails"));

                        // Decide who goes first
                        if (choice == tossResult) {
                            System.out.println(player + " wins the toss and goes first!");
                            // Player 1 = 'X', Player 2 = 'O'
                            String result = gameVsComputer(board, player, player2, sc);
                            if (result.equals(player)) score1++;
                            else if (result.equals(player2)) score2++;
                            System.out.println("Current Standings : ");
                            System.out.println(player + " : " + score1);
                            System.out.println(player2 + " : " + score2);
                            System.out.print("Play Again (Y/N) : ");
                            char temp = sc.next().toUpperCase().charAt(0);
                            playAgain = temp=='Y';
                            resetBoard(board);
                        } else {
                            System.out.println(player2 + "wins the toss and goes first!");
                            // Player 2 = 'X', Player 1 = 'O'
                            String result = gameVsComputer(board, player2, player, sc);
                            if (result.equals(player)) score1++;
                            else if (result.equals(player2)) score2++;
                            System.out.println("Current Standings : ");
                            System.out.println(player + " : " + score1);
                            System.out.println(player2 + " : " + score2);
                            System.out.print("Play Again (Y/N) : ");
                            char temp = sc.next().toUpperCase().charAt(0);
                            playAgain = temp=='Y';
                            resetBoard(board);
                        }


                    }



                } else playOn = false;

            }

        }
    }
    private static int minimax(char[][] board, int depth, boolean isMax, char aiChar, char humanChar) {
        int result = gameOver(board, aiChar);
        if (result == 1) return 10 - depth; // AI wins
        result = gameOver(board, humanChar);
        if (result == 1) return depth - 10; // Human wins
        if (isBoardFull(board)) return 0; // Draw

        if (isMax) {
            int best = Integer.MIN_VALUE;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j] == ' ') {
                        board[i][j] = aiChar;
                        best = Math.max(best, minimax(board, depth + 1, false, aiChar, humanChar));
                        board[i][j] = ' ';
                    }
                }
            }
            return best;
        } else {
            int best = Integer.MAX_VALUE;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j] == ' ') {
                        board[i][j] = humanChar;
                        best = Math.min(best, minimax(board, depth + 1, true, aiChar, humanChar));
                        board[i][j] = ' ';
                    }
                }
            }
            return best;
        }
    }

    private static boolean isBoardFull(char[][] board) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') return false;
            }
        }
        return true;
    }
    private static void makeComputerMove(char[][] board, char currentMove) {
        int bestScore = Integer.MIN_VALUE;
        int bestRow = -1, bestCol = -1;

        char humanMove = (currentMove == 'X') ? 'O' : 'X';

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    board[i][j] = currentMove;
                    int score = minimax(board, 0, false, currentMove, humanMove);
                    board[i][j] = ' ';
                    if (score > bestScore) {
                        bestScore = score;
                        bestRow = i;
                        bestCol = j;
                    }
                }
            }
        }

        if (bestRow != -1 && bestCol != -1) {
            board[bestRow][bestCol] = currentMove;
            System.out.println("Computer placed " + currentMove + " at position: " + bestRow + ", " + bestCol);
        }
    }

    private static String gameVsComputer(char[][] board, String first, String second, Scanner sc) {
        boolean gameOver = false;
        char currentMove = 'X';
        String currentPlayer = first;

        while (!gameOver) {
            display(board);
            if (currentPlayer.equals("Computer")) {
                System.out.println("Computer is making a move...");
                makeComputerMove(board, currentMove);
            } else {
                System.out.print(currentPlayer + " where do you want to place " + currentMove + " : ");
                int row = sc.nextInt();
                int col = sc.nextInt();
                if (board[row][col] == ' ') {
                    board[row][col] = currentMove;
                } else {
                    System.out.println("Invalid move. Try again.");
                    continue;
                }
            }

            int result = gameOver(board, currentMove);
            if (result == 1) {
                display(board);
                System.out.println(currentPlayer + " (" + currentMove + ") wins!");
                return currentPlayer;
            } else if (result == 0) {
                display(board);
                System.out.println("It's a draw!");
                return "";
            }

            // Switch turns
            currentPlayer = currentPlayer.equals(first) ? second : first;
            currentMove = (currentMove == 'X') ? 'O' : 'X';
        }
        return "";
    }

    private static void resetBoard(char[][] board) {
        for(int i =0;i<3;i++){
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';
            }
        }
    }
}
