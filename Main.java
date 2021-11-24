package tictactoe;
import java.util.Scanner;



public class Main {
    // declare & initialize variables
    public static Scanner sc = new Scanner(System.in);
    public static String dashes = "---------";
    public static byte xWins = 0;
    public static byte oWins = 0;
    public static int numOfX = 0;
    public static int numOfO = 0;
    public static String board = "         ";
    public static String coordinates = "";
    public static boolean gameFinished = false;
    public static char player = 'X';

    // methods used
    public static void printBoard(String board) {
        System.out.println(dashes);
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board.charAt(i * 3 + j));
                System.out.print(" ");
            }
            System.out.println("|");
        }
        System.out.println(dashes);
    }
    public static void tally(String evalRow) {
        if (evalRow.equals("XXX")) xWins += 1;
        if (evalRow.equals("OOO")) oWins += 1;
    }
    public static void checkRows(String board) {
        for (int i = 0; i < 3; i++) {
            String evalRow = "" +
                    board.charAt(0 + i * 3) +
                    board.charAt(1 + i * 3) +
                    board.charAt(2 + i * 3);
            tally(evalRow);
        }
    }
    public static void checkCols(String board) {
        for (int i = 0; i < 3; i++) {
            String evalCol = "" +
                    board.charAt(0 + i) +
                    board.charAt(3 + i) +
                    board.charAt(6 + i);
            tally(evalCol);
        }
    }
    public static void checkDiag1(String board) {
        // check top-left to bottom-right diagonal (Diag1)
        String evalDiag1 = "" +
                board.charAt(0) +
                board.charAt(4) +
                board.charAt(8);
        tally(evalDiag1);
    }
    public static void checkDiag2(String board) {
        // check top-right to bottom-left diagonal (Diag2)
        String evalDiag2 = "" +
                board.charAt(2) +
                board.charAt(4) +
                board.charAt(6);
        tally(evalDiag2);
    }
    public static void countXetO(String board) {
        numOfX = board.length() -
                board.replace("X", "").length();
        numOfO = board.length() -
                board.replace("O", "").length();
    }
    public static void getCoordinates(char player) {
        boolean isInvalid = false;
        int row = 0;
        int col = 0;
        int index = 0;

        // ask user for coordinates
        // validate coordinates before updating board
        do {
            isInvalid = false;
            System.out.print("Enter the coordinates: ");
            coordinates = sc.nextLine();
            row = (int) coordinates.charAt(0) - 48;
            col = (int) coordinates.charAt(2) - 48;

            if ((row < 0 || row > 9) || (col < 0 || col > 9)) {
                System.out.println("You should enter numbers!");
                isInvalid = true;
            } else if ((row == 0 || row > 3) || (col == 0 || col > 3)) {
                System.out.println("Coordinates should be from 1 to 3!");
                isInvalid = true;
            }
            if (!isInvalid) {
                index = (row - 1) * 3 + (col - 1);
                char cell = board.charAt(index);
                if (cell == 'X' || cell == 'O') {
                    System.out.println("This cell is occupied! Choose another one!");
                    isInvalid = true;
                }
            }
        } while (isInvalid);

        // update board
        // split String `board` to char array called chars
        char[] chars = board.toCharArray();
        // update array with move made by player
        chars[index] = player;
        // concatenate char array to string and
        // put back to String `board`
        board = String.valueOf(chars);
        printBoard(board);
    }

    // the main method 😅
    public static void main(String[] args) {
        // write your code here
            printBoard(board);
        do {

            getCoordinates(player);
            countXetO(board);
            checkRows(board);
            checkDiag1(board);
            checkDiag2(board);
            checkCols(board);

            // output winner
            if (xWins > 0 && oWins > 0) {
                System.out.println("Impossible");
            } else if (Math.abs(numOfX - numOfO) > 1) {
                System.out.println("Impossible");
            } else if (xWins > 0) {
                System.out.println("X wins");
                gameFinished = true;
            } else if (oWins > 0) {
                System.out.println("O wins");
                gameFinished = true;
            } else if (numOfX + numOfO == 9) {
                System.out.println("Draw");
                gameFinished = true;
            }

            // set next player to take a turn
            if (player == 'X') {
                player = 'O';
            } else {
                player = 'X';
            }
        } while (!gameFinished);
    }
}
