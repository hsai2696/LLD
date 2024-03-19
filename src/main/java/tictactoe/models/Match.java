package tictactoe.models;

import tictactoe.exceptions.IncorrectBoardDimensionException;
import tictactoe.exceptions.IncorrectNumberOfPlayersException;
import tictactoe.exceptions.IncorrectPositionChosenException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Match {

    GameBoard gameBoard;
    List<Player> players;
    MatchState matchState;
    public static List<String> availableSymbols = List.of("X", "O");


    public MatchState getMatchState() {
        return matchState;
    }

    public void setMatchState(MatchState matchState) {
        this.matchState = matchState;
    }

    private Match(List<Player> players, GameBoard gameBoard, MatchState matchState){
        this.players = players;
        this.gameBoard = gameBoard;
        this.matchState = matchState;

    }
    public GameBoard getGameBoard() {
        return gameBoard;
    }

    public void setGameBoard(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public static MatchBuilder getBuilder(){
        return  new MatchBuilder();
    }


    public static class MatchBuilder{
        List<Player> players = new ArrayList<>();
        GameBoard board;
        MatchState matchState;


        public MatchBuilder setDimension(int dimension) throws IncorrectBoardDimensionException {
            if(dimension < 3){
                throw new IncorrectBoardDimensionException();
            }
            this.matchState = MatchState.IN_PROGRESS;
            this.board = new GameBoard();
            this.board.setDimension(dimension);
            List<List<String>> board = new ArrayList<>();
            int rowStart = 0;
            List<Integer> availableChoices = new ArrayList<>();
            for(int j = 0 ; j < dimension * dimension; j++){
                availableChoices.add(j + 1);
            }
            this.board.setAvailableChoices(availableChoices);
            for(int i = 0; i < dimension; i++){
                List<String> row = new ArrayList<>();
                for(int j = 0; j < dimension; j++){
                    row.add(String.valueOf(rowStart+1+j));
                }
                board.add(row);
                rowStart += dimension;
            }
            this.board.setBoard(board);
            return this;
        }

        public MatchBuilder setPlayers(List<Player> players){
            this.players = players;
            return  this;
        }

        public Match build(){
            return new Match(this.players, this.board, this.matchState);
        }
    }

    public void displayBoard(){
        List<List<String>> board = this.gameBoard.getBoard();
        int boardDimension = this.gameBoard.getDimension();
        int maxDigits = String.valueOf(boardDimension * boardDimension).length();
        for(int i = 0; i < board.size(); i ++){
            List<String> row = board.get(i);
            horizontalLine(boardDimension);
            for(int col = 0 ; col < boardDimension; col++){
                String val = row.get(col);
                System.out.print("| ");
                System.out.print(val);
                int noOfSpacesToAppend = maxDigits - val.length() + 1;
                for(int s =0 ; s < noOfSpacesToAppend; s++){
                    System.out.print(" ");
                }
                System.out.print("|");
            }
            System.out.println();
            if(i == boardDimension - 1){
                horizontalLine(boardDimension);
            }

        }

    }

    private static void horizontalLine(int boardDimension) {

        int maxDigits = String.valueOf(boardDimension * boardDimension).length();
        int cellLength = 4 + maxDigits;
        for(int j = 0; j < boardDimension * cellLength; j++){
            System.out.print("-");
        }
        System.out.println();
    }

    public void play(Scanner in) throws IncorrectPositionChosenException {
        System.out.println("Starting the Game...");
        int currentPlayer = getStartingPlayer();
        while(this.matchState == MatchState.IN_PROGRESS){
            this.displayBoard();
            Player player = this.players.get(currentPlayer);
            currentPlayer = (currentPlayer + 1) %  this.players.size();
            MatchState currentState = player.makeMove(this.gameBoard, in);
            this.setMatchState(currentState);
            if(player instanceof Bot && currentState == MatchState.WIN){
                System.out.println("Bot "+currentPlayer+" is winner");
            }
        }

        this.displayBoard();
    }

    private int getStartingPlayer(){
        int noOfPlayers = this.players.size();
        return (int)Math.floor(Math.random()*10 % noOfPlayers);

    }
}
