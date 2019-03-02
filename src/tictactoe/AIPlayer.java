package tictactoe;

import java.util.ArrayList;
import java.util.Collections;

import termcolor.Color;
import termcolor.Formatter;
import termcolor.Movement;
import tictactoe.Board;
import tictactoe.Marker;
import tictactoe.Player;

public class AIPlayer implements Player {

    private int[] myMove;
    private Marker marker;
    private Marker opponentMarker;

    public AIPlayer(char marker) {
        this.marker = new Marker(marker, Color.FG_RED);
        if (marker == 'X') {
            this.opponentMarker = new Marker('O', Color.NONE);
        } else {
            this.opponentMarker = new Marker('X', Color.NONE);
        }
    }

    public AIPlayer(char marker, Color color) {
        this.marker = new Marker(marker, color);
        if (marker == 'X') {
            this.opponentMarker = new Marker('O', Color.NONE);
        } else {
            this.opponentMarker = new Marker('X', Color.NONE);
        }
    }

    public void playMove(Board board) {
        BoardEvaluator evaluator = new BoardEvaluator(board);

        Movement.clearScreen();
        System.out.println(board);
        System.out.println(Formatter.Color(Color.FG_YELLOW, "I'm thinking..."));
        minimax(board, evaluator, 0, 0, Integer.MIN_VALUE, Integer.MAX_VALUE);
        board.playMove(myMove[0], myMove[1], marker);
    }

    public void printWinMessage(boolean bothHuman) {
        System.out.println("I am better than you mere humans. mmmmmwwwwahahahahaha!");
    }
    
    public void printTieMessage(boolean bothHuman) {
        System.out.println("You cheated!  There's no way I would have tie otherwise!");
    }

    private int minimax(Board board, BoardEvaluator evaluator, int turn, int depth,
                        int alpha, int beta) {
        ArrayList<Integer> scores = new ArrayList<>();
        ArrayList<int[]> moves = new ArrayList<>();
        for(int[] move: availableMoves(board)) {
            board.playMove(move[0], move[1], (turn == 0 ? marker : opponentMarker));
            scores.add(evaluateMove(board, evaluator, turn, depth, alpha, beta));
            moves.add(move);
            if (turn== 0) {
                int maxScoreIndex = scores.indexOf(Collections.max(scores));
                myMove = moves.get(maxScoreIndex);
                int score = scores.get(maxScoreIndex);
                if (score > alpha) {
                    alpha = score;
                }
            } else {
                int minScoreIndex = scores.indexOf(Collections.min(scores));
                int score = scores.get(minScoreIndex);
                if (score < beta) {
                    beta = score;
                }
            }
            board.removeMove(move[0], move[1]);
            if (alpha >= beta) {
                break;
            }
        }
        return (turn == 0) ? alpha : beta;
    }

    private int evaluateMove(Board board, BoardEvaluator evaluator, int turn,
                             int depth, int alpha, int beta) {
        boolean isWon = evaluator.isWon();
        if (isWon && turn == 0) {
            return Integer.MAX_VALUE - depth; 
        } else if (isWon && turn == 1) {
            return depth - Integer.MAX_VALUE;
        } else if (evaluator.isTie()) {
            return 0;
        } else {
            return minimax(board, evaluator, 1-turn, depth+1, alpha, beta);
        }
    }

    private ArrayList<int[]> availableMoves(Board board) {
        ArrayList<int[]> moves = new ArrayList<>();

        for(int i = 0; i < board.getNumRows(); i++) {
            for(int j = 0; j < board.getNumCols(); j++) {
                if (board.get(i, j).equals(board.EMPTY_MARKER)) {
                    moves.add(new int[]{i, j});
                }
            }
        }
        Collections.shuffle(moves);
        return moves;
    }

}
