package tictactoe;

public interface Player {

    public void playMove(Board board);
    public void printWinMessage(boolean bothHuman);
    public void printTieMessage(boolean bothHuman);

}
