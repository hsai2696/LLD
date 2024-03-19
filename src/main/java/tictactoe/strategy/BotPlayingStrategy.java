package tictactoe.strategy;

import tictactoe.models.GameBoard;

public interface BotPlayingStrategy {
    int getPosition(GameBoard board);
}
