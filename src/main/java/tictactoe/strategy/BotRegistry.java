package tictactoe.strategy;

import tictactoe.models.DifficultyLevel;

import java.util.HashMap;
import java.util.Map;

public class BotRegistry {
    static Map<DifficultyLevel, BotPlayingStrategy> availableBots = new HashMap<>();

    public static BotPlayingStrategy getBot(DifficultyLevel difficulty){
        if(availableBots.isEmpty()){
            availableBots.put(DifficultyLevel.EASY, new EasyBot());
            availableBots.put(DifficultyLevel.MEDIUM, new MediumBot());
        }
        return availableBots.get(difficulty);
    }
}
