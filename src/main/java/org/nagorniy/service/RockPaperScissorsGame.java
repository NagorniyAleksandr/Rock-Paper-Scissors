package org.nagorniy.service;

import org.nagorniy.model.Move;
import org.nagorniy.player.ComputerPlayer;
import org.nagorniy.player.Player;
import org.nagorniy.player.UserPlayer;
import org.nagorniy.utils.GameStatisticUtils;

import java.util.Random;
import java.util.Scanner;

import static java.lang.System.out;

public class RockPaperScissorsGame implements Game {

    private static final String INVITATION_MESSAGE = "Welcome to the 'Rock-Paper-Scissors' game!\n";
    private static final String GAME_RULES_MESSAGE = "Rules:\n" +
            "A player who decides to play rock will beat another player " +
            "who has chosen scissors (\"rock crushes scissors\"),\n" +
            "but will lose to one who has played paper (\"paper covers rock\");\n" +
            "a play of paper will lose to a play of scissors (\"scissors cuts paper\").\n" +
            "If both players choose the same shape - it's draw.\n";
    private static final String GOODBYE_MESSAGE = "See you again! Have a nice day!";

    private static final String WIN_MESSAGE_FORMAT = "\n%s beats %s. You won!\n";
    private static final String LOSE_MESSAGE_FORMAT = "\n%s beats %s. You lost.\n";
    private static final String DRAW_MESSAGE = "\nDraw!";

    private static final String CHOSEN_SHAPE_MESSAGE_FORMAT = "Computer chose %s.\nYou chose %s\n";

    private static final String TERMINATE_GAME_SYMBOL = "Q";
    private static final String PLAY_AGAIN_MESSAGE_FORMAT = "Input any symbol to continue game, or '%s' to exit:\n";

    private Scanner scanner;
    private Player userPlayer;
    private Player computerPlayer;

    private int userScore = 0;
    private int computerScore = 0;
    private int numberOfGames = 0;


    public RockPaperScissorsGame(Scanner scanner, Random random) {
        this.scanner = scanner;
        userPlayer = new UserPlayer(scanner);
        computerPlayer = new ComputerPlayer(random);
    }

    private static void printGoodbye() {
        out.println(GOODBYE_MESSAGE);
    }

    private static void printInvitation() {
        out.println(INVITATION_MESSAGE);
        out.println(GAME_RULES_MESSAGE);
    }

    public void playGame() {
        printInvitation();

        do {
            Move computerMove = computerPlayer.makeMove();
            Move userMove = userPlayer.makeMove();
            out.printf(CHOSEN_SHAPE_MESSAGE_FORMAT, computerMove, userMove);

            determineWinner(userMove, computerMove);
            numberOfGames++;
        } while (playAgain());

        GameStatisticUtils.printGameStatistic(userScore, computerScore, numberOfGames);
        printGoodbye();
    }

    private void determineWinner(Move userMove, Move computerMove) {
        int compareMoves = userMove.compareMoves(computerMove);

        if (compareMoves > 0) {
            out.printf(WIN_MESSAGE_FORMAT, userMove, computerMove);
            userScore++;
        } else if (compareMoves < 0) {
            out.printf(LOSE_MESSAGE_FORMAT, computerMove, userMove);
            computerScore++;
        } else {
            out.println(DRAW_MESSAGE);
        }
    }

    private boolean playAgain() {
        out.printf(PLAY_AGAIN_MESSAGE_FORMAT, TERMINATE_GAME_SYMBOL);
        return !scanner.next().toUpperCase().equals(TERMINATE_GAME_SYMBOL);
    }
}