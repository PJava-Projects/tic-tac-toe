package tictactoe;

import tictactoe.Board;
import tictactoe.Marker;

public class BoardEvaluator {

    private Board board;

    public BoardEvaluator(Board board) {
        this.board = board;
    }

    public boolean isWon() {
        int[] lastMove = board.getLastMove();

        int majorDiagonalCount =
            countMarkerInDirection(lastMove[0], lastMove[1], -1, -1) +
            countMarkerInDirection(lastMove[0], lastMove[1], 1, 1);
        int minorDiagonalCount =
            countMarkerInDirection(lastMove[0], lastMove[1], -1, 1) +
            countMarkerInDirection(lastMove[0], lastMove[1], 1, -1);
        int horizontalCount =
            countMarkerInDirection(lastMove[0], lastMove[1], -1, 0) +
            countMarkerInDirection(lastMove[0], lastMove[1], 1, 0);
        int verticalCount =
            countMarkerInDirection(lastMove[0], lastMove[1], 0, -1) +
            countMarkerInDirection(lastMove[0], lastMove[1], 0, 1);

        return majorDiagonalCount >= 2 || minorDiagonalCount >= 2 ||
            horizontalCount >= 2 || verticalCount >= 2;
    }

    public boolean isNotOver() {
        return !isTie() && !isWon();
    }

    public boolean isTie() {
        boolean boardIsFull = true;
        for(int i = 0; i < board.getNumRows(); i++) {
            for(int j = 0; j < board.getNumCols(); j++) {
                if (board.get(i, j).equals(board.EMPTY_MARKER)) {
                    boardIsFull = false;
                }
            }
        }

        return boardIsFull && !isWon();
    }

    private int countMarkerInDirection(int x, int y, int xoffset, int yoffset) {
        Marker marker = board.get(x, y);
        x += xoffset;
        y += yoffset;

        int count = 0;
        while(x >= 0 && y >= 0 && x < board.getNumRows() && y < board.getNumCols() &&
              !board.get(x, y).equals(board.EMPTY_MARKER) &&
              board.get(x, y).equals(marker)) {
            count++;
            x += xoffset;
            y += yoffset;
        }
        return count;
    }
    
}
