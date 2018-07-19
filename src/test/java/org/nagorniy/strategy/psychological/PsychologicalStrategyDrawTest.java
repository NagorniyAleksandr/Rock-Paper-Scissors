package org.nagorniy.strategy.psychological;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.nagorniy.model.GameResult;
import org.nagorniy.model.GameRound;
import org.nagorniy.model.Move;
import org.nagorniy.strategy.ComputerStrategy;
import org.nagorniy.strategy.PsychologicalComputerStrategy;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.IntStream;

import static org.hamcrest.CoreMatchers.either;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(Parameterized.class)
public class PsychologicalStrategyDrawTest {

    private static final int TEST_ITERATIONS_AMOUNT = 20;

    private ComputerStrategy computerStrategy;

    private List<GameRound> gameHistory;
    private Move expectedMove1;
    private Move expectedMove2;

    @Before
    public void setUp() throws Exception {
        computerStrategy = new PsychologicalComputerStrategy();
    }

    public PsychologicalStrategyDrawTest(List<GameRound> gameHistory,
                                         Move expectedMove1,
                                         Move expectedMove2) {
        this.gameHistory = gameHistory;
        this.expectedMove1 = expectedMove1;
        this.expectedMove2 = expectedMove2;
    }

    @Parameterized.Parameters(name = "{index}: expected {1} or {2}")
    public static Collection moveCombinations() {

        List<GameRound> gameHistory1 = Arrays.asList(
                new GameRound(Move.ROCK, Move.ROCK, GameResult.DRAW),
                new GameRound(Move.ROCK, Move.SCISSORS, GameResult.USER_WIN),
                new GameRound(Move.SCISSORS, Move.SCISSORS, GameResult.DRAW));

        List<GameRound> gameHistory2 = Arrays.asList(
                new GameRound(Move.PAPER, Move.SCISSORS, GameResult.COMPUTER_WIN),
                new GameRound(Move.PAPER, Move.PAPER, GameResult.DRAW),
                new GameRound(Move.ROCK, Move.ROCK, GameResult.DRAW),
                new GameRound(Move.ROCK, Move.ROCK, GameResult.DRAW));

        List<GameRound> gameHistory3 = Arrays.asList(
                new GameRound(Move.ROCK, Move.ROCK, GameResult.DRAW),
                new GameRound(Move.SCISSORS, Move.PAPER, GameResult.USER_WIN),
                new GameRound(Move.SCISSORS, Move.ROCK, GameResult.COMPUTER_WIN),
                new GameRound(Move.PAPER, Move.PAPER, GameResult.DRAW));

        return Arrays.asList(new Object[][]{
                {gameHistory1, Move.PAPER, Move.ROCK},
                {gameHistory2, Move.PAPER, Move.SCISSORS},
                {gameHistory3, Move.ROCK, Move.SCISSORS}
        });
    }

    /**
     * repeat test several times to ensure random choose
     */
    @Test
    public void defineNextMoveTest() throws Exception {
        IntStream.rangeClosed(1, TEST_ITERATIONS_AMOUNT)
                .forEach(value ->
                        assertThat("The defined next move is not one from expected",
                                computerStrategy.defineNextChoice(gameHistory),
                                either(is(expectedMove1)).or(is(expectedMove2))));
    }
}