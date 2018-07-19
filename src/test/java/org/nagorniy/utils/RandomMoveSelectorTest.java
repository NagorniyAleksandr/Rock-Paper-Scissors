package org.nagorniy.utils;

import org.junit.Test;
import org.nagorniy.model.Move;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

import static org.hamcrest.CoreMatchers.either;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class RandomMoveSelectorTest {

    private static final int TEST_ITERATIONS_AMOUNT = 20;

    /**
     * repeat test several times to ensure random choose
     */
    @Test
    public void randomChoiceFromOneTest() throws Exception {
        IntStream.rangeClosed(1, TEST_ITERATIONS_AMOUNT)
                .forEach(value -> randomChoiceFromOne());
    }

    /**
     * repeat test several times to ensure random choose
     */
    @Test
    public void randomChoiceFromTwoTest() throws Exception {
        IntStream.rangeClosed(1, TEST_ITERATIONS_AMOUNT)
                .forEach(value -> randomChoiceFromTwo());
    }

    private void randomChoiceFromOne() {

        Move expectedMove = Move.PAPER;
        List<Move> movesToChoose = Collections.singletonList(expectedMove);
        Move actualMove = RandomMoveSelector.randomChoice(movesToChoose);

        assertThat("Actual move not equal to expected move",
                actualMove, is(expectedMove));
    }

    private void randomChoiceFromTwo() {

        Move expectedMove1 = Move.SCISSORS;
        Move expectedMove2 = Move.ROCK;
        List<Move> movesToChoose = Arrays.asList(expectedMove1, expectedMove2);
        Move actualMove = RandomMoveSelector.randomChoice(movesToChoose);

        assertThat("Actual move not equal to expected move",
                actualMove, either(is(expectedMove1)).or(is(expectedMove2)));
    }
}