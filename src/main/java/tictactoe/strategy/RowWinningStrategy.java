package tictactoe.strategy;

import tictactoe.models.GameBoard;

import java.util.ArrayList;
import java.util.List;

public class RowWinningStrategy implements WinningStrategy{


    @Override
    public boolean checkIfCurrentPlayerIsWinner(GameBoard board, int rowPos, int colPos, String symbol) {
        List<List<String>> gameBoard = board.getBoard();
        List<String> row = gameBoard.get(rowPos);

        for(String actualSymbol : row){
            if(!actualSymbol.equals(symbol)){
                return false;
            }
        }

        return true;
    }
}
