package org.nagorniy.strategy.statistical;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.nagorniy.model.GameResult;
import org.nagorniy.model.GameRound;
import org.nagorniy.model.Move;
import org.nagorniy.strategy.ComputerStrategy;
import org.nagorniy.strategy.StatisticalComputerStrategy;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.IntStream;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(Parameterized.class)
public class StatisticalStrategyWorstMoveTest {

    private static final int TEST_ITERATIONS_AMOUNT = 20;

    private ComputerStrategy computerStrategy;

    private List<GameRound> gameHistory;
    private Move worstMove;

    public StatisticalStrategyWorstMoveTest(List<GameRound> gameHistory, Move worstMove) {
        this.gameHistory = gameHistory;
        this.worstMove = worstMove;
    }

    @Parameterized.Parameters(name = "{index}: not expected {1}")
    public static Collection moveCombinations() {

        // game history with many GameResult.USER_WIN
        List<GameRound> gameHistory1 = Arrays.asList(
                new GameRound(Move.PAPER, Move.PAPER, GameResult.DRAW),
                new GameRound(Move.ROCK, Move.ROCK, GameResult.DRAW),
                new GameRound(Move.ROCK, Move.SCISSORS, GameResult.USER_WIN),
                new GameRound(Move.ROCK, Move.SCISSORS, GameResult.USER_WIN),
                new GameRound(Move.ROCK, Move.SCISSORS, GameResult.USER_WIN),
                new GameRound(Move.SCISSORS, Move.PAPER, GameResult.USER_WIN),
                new GameRound(Move.SCISSORS, Move.SCISSORS, GameResult.DRAW));

        // game history with many GameResult.USER_WIN
        List<GameRound> gameHistory2 = Arrays.asList(
                new GameRound(Move.PAPER, Move.PAPER, GameResult.DRAW),
                new GameRound(Move.PAPER, Move.PAPER, GameResult.DRAW),
                new GameRound(Move.PAPER, Move.PAPER, GameResult.DRAW),
                new GameRound(Move.ROCK, Move.ROCK, GameResult.DRAW),
                new GameRound(Move.PAPER, Move.ROCK, GameResult.USER_WIN),
                new GameRound(Move.PAPER, Move.ROCK, GameResult.USER_WIN),
                new GameRound(Move.SCISSORS, Move.SCISSORS, GameResult.DRAW));

        // game history with only one GameResult.USER_WIN
        List<GameRound> gameHistory3 = Arrays.asList(
                new GameRound(Move.ROCK, Move.ROCK, GameResult.DRAW),
                new GameRound(Move.SCISSORS, Move.PAPER, GameResult.USER_WIN),
                new GameRound(Move.ROCK, Move.ROCK, GameResult.DRAW),
                new GameRound(Move.PAPER, Move.PAPER, GameResult.DRAW),
                new GameRound(Move.ROCK, Move.ROCK, GameResult.DRAW),
                new GameRound(Move.SCISSORS, Move.SCISSORS, GameResult.DRAW),
                new GameRound(Move.PAPER, Move.PAPER, GameResult.DRAW));

        return Arrays.asList(new Object[][]{
                {gameHistory1, Move.SCISSORS},
                {gameHistory2, Move.ROCK},
                {gameHistory3, Move.PAPER}
        });
    }

    @Before
    public void setUp() throws Exception {
        computerStrategy = new StatisticalComputerStrategy();
    }

    /**
     * repeat test several times to ensure random choose
     */
    @Test
    public void defineWorstMoveTest() throws Exception {

        IntStream.rangeClosed(1, TEST_ITERATIONS_AMOUNT)
                .forEach(value ->
                        assertThat("The defined next is equal to worst move",
                                computerStrategy.defineNextChoice(gameHistory), is(not(worstMove))));
    }
}