package tictactoe.strategy;

import tictactoe.models.GameBoard;

public interface WinningStrategy {
    boolean checkIfCurrentPlayerIsWinner(GameBoard board, int rowPos, int colPos, String symbol);
}
