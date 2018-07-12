package org.nagorniy.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.runners.Parameterized.*;


@RunWith(Parameterized.class)
public class MoveTest {

    private Move basicMove;
    private Move otherMove;
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

    public MoveTest(Move basicMove, Move otherMove, int comparisonResult) {
        this.basicMove = basicMove;
        this.otherMove = otherMove;
        this.comparisonResult = comparisonResult;
    }

    @Test
    public void compareMoves() {
        assertThat("Incorrect result of comparison", basicMove.compareMoves(otherMove), is(comparisonResult));
    }
}