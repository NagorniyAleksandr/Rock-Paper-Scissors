package org.nagorniy.utils;

import org.nagorniy.model.GameResult;
import org.nagorniy.model.Move;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.nagorniy.model.Move.PAPER;
import static org.nagorniy.model.Move.ROCK;
import static org.nagorniy.model.Move.SCISSORS;

public class GameResultDeterminer {

    /**
     * This map holds pairs of loser-to-winner to describe 'circle-dependency'
     * and helps to answer the question "what {@code Move} beats this {@code Move}"
     * Also, this map allows us to avoid long if-else statement in comparison.
     * Every key associated with value where key=loser and value=winner,
     * For example, for {@code Move.PAPER} this map returns winner {@code SCISSORS}
     */
    private static final Map<Move, Move> LOSER_WINNER_MAP = Collections.unmodifiableMap(
            new HashMap<Move, Move>() {{
                put(ROCK, PAPER);
                put(SCISSORS, ROCK);
                put(PAPER, SCISSORS);
            }});

    public static GameResult determine(Move userMove, Move computerMove) {

        int compareMoves = compareMoves(userMove, computerMove);
        if (compareMoves == 0) return GameResult.DRAW;
        return compareMoves > 0 ? GameResult.USER_WIN : GameResult.COMPUTER_WIN;
    }



    /**
     * This method compare two moves instead of default enum's implementation of the {@code compareTo} method
     * Also, we are not allowed to override 'compereTo' method because of final
     *
     * @param basicMove the {@code Move} to be compared.
     * @param otherMove the {@code Move} to be compared.
     * @return {@code 0} if moves equal, {@code 1} the basicMove beats otherMove,
     * {@code -1} the basicMove lose otherMove
     */
    public static int compareMoves(Move basicMove, Move otherMove) {
        // draw
        if (basicMove.equals(otherMove))
            return 0;
        return LOSER_WINNER_MAP.get(basicMove).equals(otherMove) ? -1 : 1;
    }

    /**
     * Method defines the {@code Move} that beats inputted {@code Move}
     *
     * @param loser the {@code Move} to be loser.
     * @return the {@code Move} that beats inputted {@code Move}
     */
    public static Move getWinnerForMove(Move loser) {
        return LOSER_WINNER_MAP.get(loser);
    }
}
