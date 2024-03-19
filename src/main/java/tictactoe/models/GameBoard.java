package tictactoe.models;

import java.util.List;

public class GameBoard {
    int dimension;
    List<List<String>> board;
    private List<Integer> availableChoices;

    public int getDimension() {
        return dimension;
    }

    public void setDimension(int dimension) {
        this.dimension = dimension;
    }

    public List<List<String>> getBoard() {
        return board;
    }

    public void setBoard(List<List<String>> board) {
        this.board = board;
    }

    public List<Integer> getAvailableChoices() {
        return availableChoices;
    }

    public void setAvailableChoices(List<Integer> availableChoices) {
        this.availableChoices = availableChoices;
    }

}
