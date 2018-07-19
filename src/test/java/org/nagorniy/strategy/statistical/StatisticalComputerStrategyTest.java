package org.nagorniy.strategy.statistical;

import org.junit.Before;
import org.junit.Test;
import org.nagorniy.model.GameRound;
import org.nagorniy.model.Move;
import org.nagorniy.strategy.ComputerStrategy;
import org.nagorniy.strategy.StatisticalComputerStrategy;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class StatisticalComputerStrategyTest {

    private ComputerStrategy computerStrategy;

    @Before
    public void setUp() throws Exception {
        computerStrategy = new StatisticalComputerStrategy();
    }

    @Test
    public void nullHistoryTest() throws Exception {

        List<GameRound> gameHistory = null;

        computerStrategy.defineNextChoice(gameHistory);
        Move definedNextMove = computerStrategy.defineNextChoice(gameHistory);
        assertThat("The defined next is not null",
                definedNextMove, notNullValue());
    }

    @Test
    public void emptyHistoryTest() throws Exception {

        List<GameRound> gameHistory = new ArrayList<>();

        computerStrategy.defineNextChoice(gameHistory);
        Move definedNextMove = computerStrategy.defineNextChoice(gameHistory);
        assertThat("The defined next is not null",
                definedNextMove, notNullValue());
    }
}