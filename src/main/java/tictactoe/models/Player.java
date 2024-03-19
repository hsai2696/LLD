package tictactoe.models;

import tictactoe.exceptions.IncorrectPositionChosenException;

import java.util.Scanner;

public interface Player {

    MatchState makeMove(GameBoard gameBoard, Scanner in) throws IncorrectPositionChosenException;

}
