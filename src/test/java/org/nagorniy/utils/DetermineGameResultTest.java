package org.nagorniy.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.nagorniy.model.GameResult;
import org.nagorniy.model.Move;

import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.runners.Parameterized.Parameters;


@RunWith(Parameterized.class)
public class DetermineGameResultTest {

    private Move userMove;
    private Move computerMove;
    private GameResult expectedGameResult;

    @Parameters(name = "{index}: determine winner {0} vs. {1}")
    public static Collection moveCombinations() {
        return Arrays.asList(new Object[][]{
                {Move.ROCK, Move.ROCK, GameResult.DRAW},
                {Move.ROCK, Move.PAPER, GameResult.COMPUTER_WIN},
                {Move.ROCK, Move.SCISSORS, GameResult.USER_WIN},
                {Move.PAPER, Move.ROCK, GameResult.USER_WIN},
                {Move.PAPER, Move.PAPER, GameResult.DRAW},
                {Move.PAPER, Move.SCISSORS, GameResult.COMPUTER_WIN},
                {Move.SCISSORS, Move.ROCK, GameResult.COMPUTER_WIN},
                {Move.SCISSORS, Move.PAPER, GameResult.USER_WIN},
                {Move.SCISSORS, Move.SCISSORS, GameResult.DRAW}
        });
    }

    public DetermineGameResultTest(Move userMove, Move computerMove, GameResult expectedGameResult) {
        this.userMove = userMove;
        this.computerMove = computerMove;
        this.expectedGameResult = expectedGameResult;
    }

    @Test
    public void determineWinner() {
        assertThat("Incorrect result of winner determining",
                GameResultDeterminer.determine(userMove, computerMove), is(expectedGameResult));
    }
}