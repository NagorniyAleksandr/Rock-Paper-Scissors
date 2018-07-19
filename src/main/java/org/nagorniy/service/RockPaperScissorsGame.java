package org.nagorniy.service;

import org.nagorniy.model.GameResult;
import org.nagorniy.model.GameRound;
import org.nagorniy.model.Move;
import org.nagorniy.player.Player;
import org.nagorniy.player.UserPlayer;
import org.nagorniy.strategy.*;
import org.nagorniy.utils.GameResultDeterminer;
import org.nagorniy.utils.GameStatisticUtils;

import java.util.ArrayList;
import java.util.List;
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

    /**
     * This variable defines the game termination symbol.
     * Change it if you want to set other exit symbol.
     */
    private static final String TERMINATE_GAME_SYMBOL = "Q";
    private static final String PLAY_AGAIN_MESSAGE_FORMAT = "Input any symbol to continue game, or '%s' to exit:\n";
    private static final int MAX_LOSE_SEQUENCE_BEFORE_STRATEGY_CHANGE_THRESHOLD = 3;

    private Scanner scanner;
    private Player userPlayer;
    private ComputerStrategySelector computerStrategySelector;

    private List<GameRound> gameHistory = new ArrayList<>();


    public RockPaperScissorsGame(Scanner scanner) {
        this.scanner = scanner;
        userPlayer = new UserPlayer(scanner);
        initComputerStrategySelector();
    }

    /**
     * Method to invoke and high-level control the game process.
     * This method invokes:
     * - invitation printing,
     * - moves making,
     * - game termination,
     * - printing statistic at the end of the execution
     * - goodbye message printing
     */
    public void playGame() {
        printInvitation();

        do {
            Move computerMove = computerStrategySelector.makeMove(gameHistory);

            Move userMove = userPlayer.makeMove();
            out.printf(CHOSEN_SHAPE_MESSAGE_FORMAT, computerMove, userMove);

            GameResult gameResult = GameResultDeterminer.determine(userMove, computerMove);

            switch (gameResult) {
                case USER_WIN:
                    out.printf(WIN_MESSAGE_FORMAT, userMove, computerMove);
                    break;
                case COMPUTER_WIN:
                    out.printf(LOSE_MESSAGE_FORMAT, computerMove, userMove);
                    break;
                case DRAW:
                    out.println(DRAW_MESSAGE);
                    break;
            }
            gameHistory.add(new GameRound(userMove, computerMove, gameResult));
        } while (playAgain());

        GameStatisticUtils.printGameStatistic(gameHistory);
        printGoodbye();
    }

    /**
     * Method to determine the continuation of the game.
     *
     * @return {@code true} if user chose to continue the game and {@code false} in case of execution termination
     */
    private boolean playAgain() {
        out.printf(PLAY_AGAIN_MESSAGE_FORMAT, TERMINATE_GAME_SYMBOL);
        return !scanner.next().toUpperCase().equals(TERMINATE_GAME_SYMBOL);
    }

    /**
     * This method prints {@code GOODBYE_MESSAGE}
     */
    private static void printGoodbye() {
        out.println(GOODBYE_MESSAGE);
    }

    /**
     * This method prints {@code INVITATION_MESSAGE} and {@code GAME_RULES_MESSAGE}
     */
    private static void printInvitation() {
        out.println(INVITATION_MESSAGE);
        out.println(GAME_RULES_MESSAGE);
    }

    /**
     * Init method to setUp ComputerStrategies and ComputerStrategySelector
     */
    private void initComputerStrategySelector() {
        computerStrategySelector = new ComputerStrategySelector(
                MAX_LOSE_SEQUENCE_BEFORE_STRATEGY_CHANGE_THRESHOLD,
                new PsychologicalComputerStrategy(),
                new StatisticalComputerStrategy(),
                new RandomComputerStrategy());
    }
}
