package org.nagorniy.service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.StringContains.containsString;

public class RockPaperScissorsGameIntegrationTest {

    // streams for System.out interception
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    private Scanner scanner;

    private List<String> userMoves = Arrays.asList("R", "R", "R", "P", "P", "S");

    @Before
    public void setUpStreams() {

        // configure user input for test
        String userInput = String.join("\ny\n", userMoves) + "\nq";
        scanner = new Scanner(userInput);

        // make System.out interception
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void playGameTest() throws Exception {

        RockPaperScissorsGame rockPaperScissorsGame = new RockPaperScissorsGame(scanner);
        rockPaperScissorsGame.playGame();

        String outString = outContent.toString();

        assertThat("System.out must contains correct total games number",
                outString, containsString("Total games......" + userMoves.size()));
        assertThat("System.out must contains 'You chose PAPER'",
                outString, containsString("You chose PAPER"));
        assertThat("System.out must contains 'You chose ROCK'",
                outString, containsString("You chose ROCK"));
        assertThat("System.out must contains 'You chose SCISSORS'",
                outString, containsString("You chose SCISSORS"));
    }
}