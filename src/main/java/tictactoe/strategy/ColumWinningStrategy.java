package tictactoe.strategy;

import tictactoe.models.GameBoard;

import java.util.ArrayList;
import java.util.List;

public class ColumWinningStrategy implements WinningStrategy{
    @Override
    public boolean checkIfCurrentPlayerIsWinner(GameBoard board, int rowPos, int colPos, String symbol) {
        List<List<String>> gameBoard = new ArrayList<>(board.getBoard());

        for(List<String> row: gameBoard){
            String actualSymbol = row.get(colPos);
            if(!actualSymbol.equals(symbol)){
                return false;
            }

        }

        return true;
    }
}
