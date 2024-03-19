package tictactoe.models;

import tictactoe.exceptions.IncorrectPositionChosenException;
import tictactoe.strategy.WinningStrategy;
import tictactoe.strategy.WinningStrategyRegistry;

import java.util.List;
import java.util.Scanner;

public class Human implements Player {

    String email;
    String name;
    String profilePicture;
    String symbol;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }


    @Override
    public MatchState makeMove(GameBoard board, Scanner in) throws IncorrectPositionChosenException {
        if(board.getAvailableChoices().size() == 0){
            return MatchState.DRAW;
        }
        System.out.println("Please choose position to mark: ");
        Integer position = Integer.parseInt(in.nextLine());
        int dimension = board.getDimension();

        int rowPos = (int) Math.floor((position - 1) / dimension);
        int colPos = (int) Math.floor((position - 1) % dimension);
        if (rowPos > dimension || colPos > dimension || rowPos < 0 || colPos < 0) {
            throw new IncorrectPositionChosenException("Kindly specify valid position to mark");
        }
        if (board.getAvailableChoices().indexOf(position) == -1) {
            throw new IncorrectPositionChosenException("Already Marked position choose a vacant one");
        }

        board.getBoard().get(rowPos).set(colPos, this.getSymbol());
        board.getAvailableChoices().remove(position);
        List<WinningStrategy> strategyList = WinningStrategyRegistry.getAllStrategies();
        boolean winner = false;
        for(WinningStrategy strategy: strategyList){
            winner = strategy.checkIfCurrentPlayerIsWinner(board, rowPos, colPos, this.getSymbol());
            if(winner){
                System.out.println("congratulations.... Player"+this.getName()+" is winner");
                return MatchState.WIN;
            }
        }

        return MatchState.IN_PROGRESS;
    }
}
