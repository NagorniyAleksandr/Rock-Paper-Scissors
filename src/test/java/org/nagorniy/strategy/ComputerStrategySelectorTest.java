package org.nagorniy.strategy;

import org.junit.Before;
import org.junit.Test;
import org.nagorniy.model.GameResult;
import org.nagorniy.model.GameRound;
import org.nagorniy.model.Move;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class ComputerStrategySelectorTest {

    private ComputerStrategySelector strategySelector;

    private StatisticalComputerStrategy mockedStatisticalStrategy;
    private PsychologicalComputerStrategy mockedPsychologicalStrategy;


    @Before
    public void setUp() throws Exception {

        mockedStatisticalStrategy = mock(StatisticalComputerStrategy.class);
        when(mockedStatisticalStrategy.defineNextChoice(any()))
                .thenReturn(Move.ROCK);

        mockedPsychologicalStrategy = mock(PsychologicalComputerStrategy.class);
        when(mockedStatisticalStrategy.defineNextChoice(any()))
                .thenReturn(Move.PAPER);
        strategySelector = new ComputerStrategySelector(2,
                mockedStatisticalStrategy,
                mockedPsychologicalStrategy);
    }

    @Test
    public void makeMove() throws Exception {

        List<GameRound> gameHistory = new ArrayList<>();

        strategySelector.makeMove(gameHistory);
        verify(mockedStatisticalStrategy, times(1)).defineNextChoice(gameHistory);

        gameHistory.add(new GameRound(Move.ROCK, Move.PAPER, GameResult.COMPUTER_WIN));
        strategySelector.makeMove(gameHistory);
        verify(mockedStatisticalStrategy, times(2)).defineNextChoice(gameHistory);

        gameHistory.add(new GameRound(Move.ROCK, Move.SCISSORS, GameResult.USER_WIN));
        strategySelector.makeMove(gameHistory);
        verify(mockedStatisticalStrategy, times(3)).defineNextChoice(gameHistory);

        // check if strategy was changed
        gameHistory.add(new GameRound(Move.SCISSORS, Move.PAPER, GameResult.USER_WIN));
        strategySelector.makeMove(gameHistory);
        verify(mockedStatisticalStrategy, times(3)).defineNextChoice(gameHistory);
        verify(mockedPsychologicalStrategy, times(1)).defineNextChoice(gameHistory);

        // check if strategy is still changed
        gameHistory.add(new GameRound(Move.SCISSORS, Move.ROCK, GameResult.COMPUTER_WIN));
        strategySelector.makeMove(gameHistory);
        verify(mockedStatisticalStrategy, times(3)).defineNextChoice(gameHistory);
        verify(mockedPsychologicalStrategy, times(2)).defineNextChoice(gameHistory);
    }
}