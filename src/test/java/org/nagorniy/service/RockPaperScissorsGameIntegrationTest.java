package org.nagorniy.service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.nagorniy.model.Move;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.StringContains.containsString;
import static org.junit.runners.Parameterized.Parameters;
import static org.mockito.Mockito.*;


@RunWith(Parameterized.class)
public class RockPaperScissorsGameIntegrationTest {

    // streams for System.out interception
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    private Scanner scanner;
    private Random mockRandom;

    // parametrized values
    private Move computerMove;
    private int wins;
    private int losses;
    private int draws;

    private List<String> userMoves = Arrays.asList("R", "R", "R", "P", "P", "S");

    public RockPaperScissorsGameIntegrationTest(Move computerMove, int wins, int losses, int draws) {
        this.computerMove = computerMove;
        this.wins = wins;
        this.losses = losses;
        this.draws = draws;
    }

    @Before
    public void setUpStreams() {

        // configure user input for test
        String userInput = String.join("\ny\n", userMoves) + "\nq";
        scanner = new Scanner(userInput);

        //create a mock random
        mockRandom = mock(Random.class);
        //set up the random with only one value
        when(mockRandom.nextInt(3)).thenReturn(computerMove.ordinal());

        // make System.out interception
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Parameters(name = "{index}: computer plays only {0}, wins: {1}, losses: {2}, draws: {3}")
    public static Collection gameStatisticCombinations() {
        return Arrays.asList(new Object[][]{
                {Move.ROCK, 2, 1, 3},
                {Move.PAPER, 1, 3, 2},
                {Move.SCISSORS, 3, 2, 1}
        });
    }

    @Test
    public void computerPlaysOnlyRockTest() throws Exception {

        RockPaperScissorsGame rockPaperScissorsGame = new RockPaperScissorsGame(scanner, mockRandom);
        rockPaperScissorsGame.playGame();

        String outString = outContent.toString();

        assertThat("System.out must contains correct wins number",
                outString, containsString("Wins............." + wins));
        assertThat("System.out must contains correct losses number",
                outString, containsString("Losses..........." + losses));
        assertThat("System.out must contains correct draws number",
                outString, containsString("Draws............" + draws));

        //verify that mocked method was invoked every user move
        verify(mockRandom, times(userMoves.size())).nextInt(3);
    }
}