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
public class GetWinnerForMoveTest {

    private Move inputMove;
    private Move expectedWinnerMove;

    @Parameters(name = "{index}: get winner for {0}")
    public static Collection moveCombinations() {
        return Arrays.asList(new Object[][]{
                {Move.ROCK, Move.PAPER},
                {Move.PAPER, Move.SCISSORS},
                {Move.SCISSORS, Move.ROCK}
        });
    }

    public GetWinnerForMoveTest(Move inputMove, Move expectedWinnerMove) {
        this.inputMove = inputMove;
        this.expectedWinnerMove = expectedWinnerMove;
    }

    @Test
    public void getWinner() {
        assertThat("Incorrect result of getting winner",
                GameResultDeterminer.getWinnerForMove(inputMove), is(expectedWinnerMove));
    }
}