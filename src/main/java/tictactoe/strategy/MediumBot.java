package tictactoe.strategy;

import tictactoe.models.GameBoard;

public class MediumBot implements BotPlayingStrategy{
    @Override
    public int getPosition(GameBoard board) {
        int availableChoices = board.getAvailableChoices().size();
        return board.getAvailableChoices().get(availableChoices-1);
    }
}
