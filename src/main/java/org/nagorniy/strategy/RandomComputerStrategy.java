package org.nagorniy.strategy;

import org.nagorniy.model.GameRound;
import org.nagorniy.model.Move;
import org.nagorniy.utils.RandomMoveSelector;

import java.util.List;

public class RandomComputerStrategy implements ComputerStrategy {

    /**
     * Method to define next computer's move with totally random move
     *
     * @return random move
     */
    @Override
    public Move defineNextChoice(List<GameRound> gameHistory) {
        return RandomMoveSelector.randomChoice(MOVE_VALUES);
    }
}
