package org.nagorniy.strategy;

import org.nagorniy.model.GameResult;
import org.nagorniy.model.GameRound;
import org.nagorniy.model.Move;
import org.nagorniy.utils.RandomMoveSelector;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.counting;


public class StatisticalComputerStrategy implements ComputerStrategy {

    /**
     * This strategy makes decision about the next computer move based on previous round statistic.
     * <p>
     * The algorithm finds the best move(that won most often).
     * <p>
     * If there are no wins(jet), algorithm finds the worst move(that lose most often),
     * and choose next move between two left moves(except calculated the worst move).
     * <p>
     * If the game history is empty(no games played)
     * or there are no luckiest and worst moves(only draws) - algorithm choose random move.
     *
     * @param gameHistory list with results of previous rounds.
     * @return next computer {@code Move}
     */
    @Override
    public Move defineNextChoice(List<GameRound> gameHistory) {

        if (gameHistory == null || gameHistory.size() == 0) {
            return RandomMoveSelector.randomChoice(MOVE_VALUES);
        } else {
            Optional<Move> luckiestComputerMove = gameHistory
                    .stream()
                    .filter(gameRound -> GameResult.COMPUTER_WIN.equals(gameRound.getGameResult()))
                    .collect(Collectors.groupingBy(GameRound::getComputerMove, counting()))
                    .entrySet()
                    .stream()
                    .max(Map.Entry.comparingByValue())
                    .map(Map.Entry::getKey);

            Optional<Move> worstComputerMove = gameHistory
                    .stream()
                    .filter(gameRound -> GameResult.USER_WIN.equals(gameRound.getGameResult()))
                    .collect(Collectors.groupingBy(GameRound::getComputerMove, counting()))
                    .entrySet()
                    .stream()
                    .max(Map.Entry.comparingByValue())
                    .map(Map.Entry::getKey);

            return luckiestComputerMove.orElseGet(() -> RandomMoveSelector
                    .randomChoice(MOVE_VALUES
                            .stream()
                            .filter(move -> !move.equals(worstComputerMove.orElseGet(null)))
                            .collect(Collectors.toList())));
        }
    }
}
