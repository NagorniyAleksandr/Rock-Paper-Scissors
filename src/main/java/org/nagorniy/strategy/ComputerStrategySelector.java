package org.nagorniy.strategy;

import org.nagorniy.model.GameResult;
import org.nagorniy.model.GameRound;
import org.nagorniy.model.Move;

import java.util.Arrays;
import java.util.List;

public class ComputerStrategySelector {

    private final List<ComputerStrategy> availableStrategies;
    private int maxLoseSequenceThreshold;
    private ComputerStrategy currentStrategy;

    /**
     * Constructor with required parameters.
     * The first of strategies will be set as the first current strategy
     *
     * {@code maxLoseSequenceThreshold} must be positive number
     * {@code strategies} must be not empty
     *
     * @param maxLoseSequenceThreshold
     * @param strategies
     */
    public ComputerStrategySelector(int maxLoseSequenceThreshold, ComputerStrategy... strategies) {
        this.maxLoseSequenceThreshold = maxLoseSequenceThreshold;
        this.currentStrategy = strategies[0];
        this.availableStrategies = Arrays.asList(strategies);
    }

    /**
     * This method changes current strategy if needed and define the next move
     *
     * @param gameResults game history
     * @return the next computer's move
     */
    public Move makeMove(List<GameRound> gameResults) {
        currentStrategy = defineStrategy(gameResults);
        return currentStrategy.defineNextChoice(gameResults);
    }

    /**
     * This method returns another computer strategy if needToChangeStrategy == true,
     * Also, method returns current strategy if
     * - there is not enough history
     * - there is 1 or less available strategies
     * - method {@code needToChangeStrategy} returns {@code false}
     *
     * @param gameResults game history
     * @return computer strategy to make next move
     */
    private ComputerStrategy defineStrategy(List<GameRound> gameResults) {

        if (gameResults == null ||
                gameResults.size() < maxLoseSequenceThreshold ||
                availableStrategies == null ||
                availableStrategies.size() <= 1 ||
                !needToChangeStrategy(gameResults)) {
            return currentStrategy;
        } else {
            return availableStrategies
                    .stream()
                    .filter(computerStrategy -> !computerStrategy.equals(currentStrategy))
                    .findAny().orElse(currentStrategy);
        }
    }

    /**
     * Method check if the last {@code maxLoseSequenceThreshold} games in history
     * computer lose(game result equals to {@code GameResult.USER_WIN})
     *
     * @param gameRounds game history
     * @return true is needed to change strategy
     */
    private boolean needToChangeStrategy(List<GameRound> gameRounds) {
        return gameRounds
                .stream()
                .skip(gameRounds.size() - maxLoseSequenceThreshold)
                .map(GameRound::getGameResult)
                .allMatch(GameResult.USER_WIN::equals);
    }
}
