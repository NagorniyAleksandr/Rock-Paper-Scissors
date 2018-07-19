package org.nagorniy.strategy;

import org.nagorniy.model.GameRound;
import org.nagorniy.model.Move;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public interface ComputerStrategy {

    List<Move> MOVE_VALUES = Arrays.asList(Move.values());

    Move defineNextChoice(List<GameRound> gameHistory);
}
