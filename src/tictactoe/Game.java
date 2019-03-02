package tictactoe;

import termcolor.Movement;

public class Game {

    private Board board;
    private BoardEvaluator evaluator;
    private Player[] players;
    private boolean bothHumans;
    private int turn;

    public Game(Board board, Player[] players, int turn, boolean bothHumans) {
        this.board = board;
        this.evaluator = new BoardEvaluator(board);
        this.players = players;
        this.turn = turn;
        this.bothHumans = bothHumans;
    }

    public void run() {
        while (evaluator.isNotOver()) {
            players[turn].playMove(board);
            if (evaluator.isWon()) {
                Movement.clearScreen();
                System.out.println(board);
                players[turn].printWinMessage(bothHumans);
            } else if (evaluator.isTie()) {
                Movement.clearScreen();
                System.out.println(board);
                players[turn].printTieMessage(bothHumans);
            } else {
                turn = 1 - turn;
            }
        }
    }
    
}
