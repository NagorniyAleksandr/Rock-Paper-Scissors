package org.nagorniy.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public enum Move {
    ROCK, PAPER, SCISSORS;

    // this map holds pairs of winner-to-looser to avoid long if-else statement in comparison
    private static Map<Move, Move> winnerLooserMap = Collections.unmodifiableMap(
            new HashMap<Move, Move>() {{
                put(ROCK, SCISSORS);
                put(SCISSORS, PAPER);
                put(PAPER, ROCK);
            }});

    // method to compare two moves instead of default enum's implementation of the 'compareTo' method
    public int compareMoves(Move otherMove) {
        // draw
        if (this.equals(otherMove))
            return 0;

        return winnerLooserMap.get(this).equals(otherMove) ? 1 : -1;
    }
}
