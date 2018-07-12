package org.nagorniy.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public enum Move {
    ROCK, PAPER, SCISSORS;

    /**
     * This map holds pairs of winner-to-looser to describe 'circle-dependency'
     * and avoid long if-else statement in comparison.
     * Every key associated with value where key=winner and value=looser,
     */
    private static Map<Move, Move> winnerLooserMap = Collections.unmodifiableMap(
            new HashMap<Move, Move>() {{
                put(ROCK, SCISSORS);
                put(SCISSORS, PAPER);
                put(PAPER, ROCK);
            }});

    /**
     * This method compare two moves instead of default enum's implementation of the {@code compareTo} method
     * Also, we are not allowed to override 'compereTo' method because of final
     *
     * @param otherMove the {@code Move} to be compared.
     * @return {@code 0} for draw, {@code 1} for win, {@code -1} for lose
     */
    public int compareMoves(Move otherMove) {
        // draw
        if (this.equals(otherMove))
            return 0;
        return winnerLooserMap.get(this).equals(otherMove) ? 1 : -1;
    }
}
