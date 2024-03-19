package tictactoe.strategy;

import java.util.ArrayList;
import java.util.List;

public class WinningStrategyRegistry {
    static List<WinningStrategy> strategyList;
    public static List<WinningStrategy> getAllStrategies(){
        if(strategyList == null){
            strategyList = new ArrayList<>();
            strategyList.add(new RowWinningStrategy());
            strategyList.add(new ColumWinningStrategy());
        }
        return strategyList;
    }

}
