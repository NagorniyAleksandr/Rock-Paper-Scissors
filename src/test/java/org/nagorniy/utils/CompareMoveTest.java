package org.nagorniy.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.nagorniy.model.Move;

import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.runners.Parameterized.*;


@RunWith(Parameterized.class)
public class CompareMoveTest {

    private Move basicMove;
    private Move moveToCompare;
    private int comparisonResult;

    @Parameters(name = "{index}: compare {0} to {1}")
    public static Collection moveCombinations() {
        return Arrays.asList(new Object[][]{
                {Move.ROCK, Move.ROCK, 0},
                {Move.ROCK, Move.PAPER, -1},
                {Move.ROCK, Move.SCISSORS, 1},
                {Move.PAPER, Move.ROCK, 1},
                {Move.PAPER, Move.PAPER, 0},
                {Move.PAPER, Move.SCISSORS, -1},
                {Move.SCISSORS, Move.ROCK, -1},
                {Move.SCISSORS, Move.PAPER, 1},
                {Move.SCISSORS, Move.SCISSORS, 0}
        });
    }

    public CompareMoveTest(Move basicMove, Move moveToCompare, int comparisonResult) {
        this.basicMove = basicMove;
        this.moveToCompare = moveToCompare;
        this.comparisonResult = comparisonResult;
    }

    @Test
    public void compareMoves() {
        assertThat("Incorrect result of comparison",
                GameResultDeterminer.compareMoves(basicMove, moveToCompare), is(comparisonResult));
    }
}