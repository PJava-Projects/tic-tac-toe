package tictactoe;

import java.util.Scanner;

import termcolor.Color;
import termcolor.Formatter;
import termcolor.Movement;
import tictactoe.Board;
import tictactoe.Marker;
import tictactoe.Player;

public class HumanPlayer implements Player {

    private Marker marker;

    public HumanPlayer(char marker) {
        this.marker = new Marker(marker, Color.FG_BLUE);
    }

    public HumanPlayer(char marker, Color color) {
        this.marker = new Marker(marker, color);
    }

    public void playMove(Board board) {
        String errMsg = Formatter.Color(Color.FG_RED,
                                        "Sorry, a marker is already placed there!");

        int[] move = getMove(board, null);
        while (!board.playMove(move[0]-1, move[1]-1, marker)) {
            move = getMove(board, errMsg);
        }
    }

    public void printWinMessage(boolean bothHuman) {
        if (bothHuman) {
            System.out.printf("Player %s has won!\n", marker);
        } else {
            System.out.println("You have won!");
        }
    }

    public void printTieMessage(boolean bothHuman) {
        System.out.println("There was a tie...");
    }

    private int[] getMove(Board board, String error) {
        Movement.clearScreen();
        System.out.println(board);

        if (error == null) {
            System.out.println("It is currently your turn.\n");
        } else {
            System.out.println(error);
        }
        String rowPrompt = "Which row would you like to place your marker in? ";
        int row = getUserInput(rowPrompt, "row");
        System.out.println();
        String colPrompt = "Which column would you like to place your marker in? ";
        int col = getUserInput(colPrompt, "col");
        System.out.println();

        return new int[]{row, col};
    }

    private int getUserInput(String prompt, String type) {
        String errMsg = String.format("That's not a valid %s number. Please try again.",
                                      type);
        errMsg = Formatter.Color(Color.FG_RED, errMsg);
        boolean hasErr = false;
        Scanner s = new Scanner(System.in);

        System.out.print(prompt);
        System.out.flush();
        int digit = 0;
        while (digit == 0) {
            try {
                if (s.hasNextLine()) {
                    String line = s.nextLine();
                    digit = Integer.parseInt(line);
                    if (digit < 1 || digit > 3) {
                        digit = 0;
                        throw new NumberFormatException();
                    }
                }
            } catch (NumberFormatException e) {
                Movement.setCursorUp(1);
                Movement.clearRestOfLine();
                if (!hasErr) {
                    System.out.println(errMsg);
                }
                System.out.print(prompt);
                System.out.flush();
                hasErr = true;
            }
        }

        return digit;
    }
    
}
