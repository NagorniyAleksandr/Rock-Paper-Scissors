package org.nagorniy.player;

import org.nagorniy.model.Move;

import java.util.Random;


public class ComputerPlayer implements Player {

    private static final Move[] MOVE_VALUES = Move.values();
    private final Random random;

    public ComputerPlayer(Random random) {
        this.random = random;
    }

    public Move makeMove() {
        return MOVE_VALUES[random.nextInt(MOVE_VALUES.length)];
    }
}
