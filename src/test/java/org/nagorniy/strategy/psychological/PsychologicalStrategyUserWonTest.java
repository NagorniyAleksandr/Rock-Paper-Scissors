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

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(Parameterized.class)
public class PsychologicalStrategyUserWonTest {

    private ComputerStrategy computerStrategy;

    private List<GameRound> gameHistory;
    private Move expectedMove;

    @Before
    public void setUp() throws Exception {
        computerStrategy = new PsychologicalComputerStrategy();
    }

    public PsychologicalStrategyUserWonTest(List<GameRound> gameHistory, Move expectedMove) {
        this.gameHistory = gameHistory;
        this.expectedMove = expectedMove;
    }

    @Parameterized.Parameters(name = "{index}: expected {1}")
    public static Collection moveCombinations() {

        List<GameRound> gameHistory1 = Arrays.asList(
                new GameRound(Move.ROCK, Move.ROCK, GameResult.DRAW),
                new GameRound(Move.ROCK, Move.SCISSORS, GameResult.USER_WIN),
                new GameRound(Move.SCISSORS, Move.PAPER, GameResult.USER_WIN));

        List<GameRound> gameHistory2 = Arrays.asList(
                new GameRound(Move.PAPER, Move.SCISSORS, GameResult.COMPUTER_WIN),
                new GameRound(Move.PAPER, Move.PAPER, GameResult.DRAW),
                new GameRound(Move.ROCK, Move.ROCK, GameResult.DRAW),
                new GameRound(Move.ROCK, Move.SCISSORS, GameResult.USER_WIN));

        List<GameRound> gameHistory3 = Arrays.asList(
                new GameRound(Move.ROCK, Move.ROCK, GameResult.DRAW),
                new GameRound(Move.SCISSORS, Move.PAPER, GameResult.USER_WIN),
                new GameRound(Move.SCISSORS, Move.ROCK, GameResult.COMPUTER_WIN),
                new GameRound(Move.PAPER, Move.ROCK, GameResult.USER_WIN));

        return Arrays.asList(new Object[][]{
                {gameHistory1, Move.SCISSORS},
                {gameHistory2, Move.ROCK},
                {gameHistory3, Move.PAPER}
        });
    }

    @Test
    public void defineNextMoveTest() throws Exception {

        Move definedNextMove = computerStrategy.defineNextChoice(gameHistory);
        assertThat("The defined next is not equal to expected",
                definedNextMove, is(expectedMove));
    }

}