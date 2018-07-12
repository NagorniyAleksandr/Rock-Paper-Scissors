package org.nagorniy.utils;

public class GameStatisticUtils {

    private static final String GAME_STATISTIC_MESSAGE_FORMAT =
            "-------------------------\n" +
                    "Games statistic:\n" +
                    "Total games......%d\n" +
                    "Wins.............%d\n" +
                    "Losses...........%d\n" +
                    "Draws............%d\n" +
                    "Percentage win...%.2f%%\n" +
                    "-------------------------\n";

    /**
     * This method prints statistic of games
     * with calculated draws amount and wins percentage
     *
     * @param wins          amount of wins
     * @param losses        amount of losses
     * @param numberOfGames played games amount
     */
    public static void printGameStatistic(int wins, int losses, int numberOfGames) {
        int draws = numberOfGames - wins - losses;
        double percentageWin = ((double) wins * 100) / numberOfGames;

        System.out.printf(GAME_STATISTIC_MESSAGE_FORMAT,
                numberOfGames, wins, losses, draws, percentageWin);
    }
}
