package tictactoe;

import tictactoe.exceptions.IncorrectBoardDimensionException;
import tictactoe.exceptions.IncorrectPositionChosenException;
import tictactoe.models.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) throws IncorrectPositionChosenException {
        Match m;
        Scanner in = new Scanner(System.in);

        try{
            List<Player> players = getPlayers(in);
            m = Match.getBuilder().setDimension(3).setPlayers(players).build();
            m.play(in);
        } catch (IncorrectBoardDimensionException e) {
            throw new RuntimeException("incorrect dimension please set it above 3");
        }finally {
            in.close();
        }


    }

    private static List<Player> getPlayers(Scanner in) {
        List<Player> players = new ArrayList<>();
        for(int i=0;i < 2; i++){
            Player p = null;
            // getting data for the players as we know there are only two players
            System.out.println("choose player "+(i+1)+": ");
            System.out.println("1. Human");
            System.out.println("2. Bot");
            int playerTypeChoice = Integer.parseInt(in.nextLine());
            String symbol = Match.availableSymbols.get(i);
            if(playerTypeChoice == 1){
                Human h = createHumanPlayer(in, symbol);
                players.add(h);
            }else{
                Bot b = createBot(in, symbol);
                players.add(b);

            }

        }
        return players;
    }

    private static Bot createBot(Scanner in, String symbol) {
        Bot b = new Bot();
        System.out.println("1.Easy");
        System.out.println("2.Medium");

        System.out.println("Please Choose Difficulty: ");
        int choice = Integer.parseInt(in.nextLine());
        if(choice == 1){
            b.setDifficultyLevel(DifficultyLevel.EASY);
        }else if(choice == 2){
            b.setDifficultyLevel(DifficultyLevel.MEDIUM);
        }else{
            throw new RuntimeException("Invalid Choice");
        }
        b.setSymbol(symbol);
        return b;
    }

    private static Human createHumanPlayer(Scanner in, String symbol) {
        Human h = new Human();
        System.out.println("Please Enter Name: ");
        h.setName(in.nextLine());
        System.out.println("Please Enter email: ");
        h.setEmail(in.nextLine());
        System.out.println("Please Enter profile pic: ");
        h.setProfilePicture(in.nextLine());
        h.setSymbol(symbol);
        return h;
    }
}
