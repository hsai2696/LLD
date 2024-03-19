package tictactoe.models;

import tictactoe.strategy.BotPlayingStrategy;
import tictactoe.strategy.BotRegistry;
import tictactoe.strategy.WinningStrategy;
import tictactoe.strategy.WinningStrategyRegistry;

import java.util.List;
import java.util.Scanner;

public class Bot implements Player {
    DifficultyLevel difficultyLevel;
    String symbol;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
    public DifficultyLevel getDifficultyLevel() {
        return difficultyLevel;
    }

    public void setDifficultyLevel(DifficultyLevel difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }

    @Override
    public MatchState makeMove(GameBoard board, Scanner in) {
        if(board.getAvailableChoices().size() == 0){
            return MatchState.DRAW;
        }
        BotPlayingStrategy bot = BotRegistry.getBot(this.getDifficultyLevel());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        Integer position = bot.getPosition(board);
        int dimension = board.getDimension();
        int rowPos = (int) Math.floor((position -1)  / dimension);
        int colPos = (int)Math.floor((position - 1) % dimension);
        board.getBoard().get(rowPos).set(colPos, this.getSymbol());
        board.getAvailableChoices().remove(position);
        List<WinningStrategy> strategyList = WinningStrategyRegistry.getAllStrategies();
        boolean winner = false;
        for(WinningStrategy strategy: strategyList){
            winner = strategy.checkIfCurrentPlayerIsWinner(board, rowPos, colPos, this.getSymbol());
            if(winner){
                return MatchState.WIN;
            }
        }

        return MatchState.IN_PROGRESS;

    }
}
