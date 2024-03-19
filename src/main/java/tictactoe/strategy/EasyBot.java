package tictactoe.strategy;

import tictactoe.models.GameBoard;

public class EasyBot implements BotPlayingStrategy{

    @Override
    public int getPosition(GameBoard board) {
        return board.getAvailableChoices().get(0);
    }
}
