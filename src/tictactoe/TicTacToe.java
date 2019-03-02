package tictactoe;

import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

import termcolor.Color;
import termcolor.Formatter;
import termcolor.Movement;

public class TicTacToe {

    private static void printIntroduction() {
        StringBuffer sb = new StringBuffer();
        sb.append("Welcome to TicTacToe by PChan!\n");
        sb.append("In this version, you can choose to play agains a bot or ");
        sb.append("you can choose to play against another person.\n");

        Movement.clearScreen();
        System.out.println(sb.toString());
    }

    private static Player[] getPlayers() {
        System.out.println("1) Another person");
        System.out.println("2) The bot");
        System.out.println("Enter 1 to choose another person and 2 to choose the bot.");

        String playerPrompt = "Who would you like to play against? ";
        int playerSelection = getUserInput(playerPrompt, 1, 2);
        System.out.println();

        System.out.println("1) X");
        System.out.println("2) O");
        System.out.println("Enter 1 choose X as your marker and 2 to choose O.");

        String markerPrompt = "Which marker would you like to use? ";
        int markerSelection = getUserInput(markerPrompt, 1, 2);
        System.out.println();

        char marker = (markerSelection == 1) ? 'X' : 'O';
        char opponentMarker = (marker == 'X') ? 'O' : 'X';
        if (playerSelection == 1) {
            return new Player[]{
                new HumanPlayer(marker),
                new HumanPlayer(opponentMarker)
            };
        } else {
            return new Player[]{
                new HumanPlayer(marker),
                new AIPlayer(opponentMarker)
            };
        }
    }
            
    private static int getTurn() {
        System.out.println("1) Yes");
        System.out.println("2) No");
        System.out.println("3) Random");
        System.out.println("Enter 1 to go first, 2 to go second, 3 for random.");

        String turnPrompt = "Would you like to start the game? ";
        int turnSelection = getUserInput(turnPrompt, 1, 3);

        if (turnSelection == 3) {
            return ThreadLocalRandom.current().nextInt(0, 2);
        }
        return turnSelection - 1;
    } 

    private static int getUserInput(String prompt, int lowerRange, int upperRange) {
        String errMsg = Formatter.Color(Color.FG_RED,
                                        "That's not a valid choice. Please try again.");
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
                    if (digit < lowerRange || digit > upperRange) {
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

    public static void main(String[] args) {
        printIntroduction();
        Player[] players = getPlayers();
        int turn = getTurn();
        Board board = new Board(3, 3);
        boolean bothHumans = (players[1] instanceof HumanPlayer);

        Game g = new Game(board, players, turn, bothHumans);
        g.run();
    }

}
