package tictactoe;

import termcolor.Color;
import termcolor.Formatter;

public class Board {

    private int numRows, numCols;
    private Marker[][] board;
    private int[] lastMove = new int[2];
    public static final Marker EMPTY_MARKER = new Marker(' ', Color.NONE);

    public Board(int numRows, int numCols) {
        this.numRows = numRows;
        this.numCols = numCols;
        board = new Marker[numRows][numCols];
        for(int i = 0; i < numRows; i++) {
            for(int j = 0; j < numCols; j++) {
                board[i][j] = EMPTY_MARKER;
            }
        }
    }

	/**
	 * @return the numRows
	 */
	public int getNumRows() {
		return numRows;
	}

	/**
	 * @return the numCols
	 */
	public int getNumCols() {
		return numCols;
	}

    public Marker get(int x, int y) {
        return board[x][y];
    }

    public int[] getLastMove() {
        return lastMove;
    }

    public boolean playMove(int x, int y, Marker marker) {
        if (x >= 0 && y >= 0 && x < numRows && y < numCols &&
            this.get(x, y).equals(EMPTY_MARKER)) {
            board[x][y] = marker;
            lastMove[0] = x;
            lastMove[1] = y;
            return true;
        }
        return false;
    }

    public boolean removeMove(int x, int y) {
        if (x >= 0 && y>= 0 && x < numRows && y < numCols &&
            !this.get(x, y).equals(EMPTY_MARKER)) {
            board[x][y] = EMPTY_MARKER;
            return true;
        }
        return false;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("   1   2   3\n");
        for(int i = 0; i < numRows; i++) {
            sb.append(i+1);
            sb.append(" ");
            for(int j = 0; j < numCols; j++) {
                sb.append(" ");
                if (lastMove[0] == i && lastMove[1] == j) {
                    sb.append(Formatter.Color(Color.FG_GREEN, board[i][j].toString()));
                } else {
                    sb.append(board[i][j]);
                }
                sb.append(" ");
                if (j < 2) {
                    sb.append("|");
                }
            }
            if (i < 2) {
                sb.append("\n  -----------\n");
            }
        }
        sb.append("\n");
        return sb.toString();
    }

}
