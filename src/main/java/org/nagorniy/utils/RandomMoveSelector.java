package org.nagorniy.utils;

import org.nagorniy.model.Move;

import java.util.List;
import java.util.Random;

public class RandomMoveSelector {

    private static Random random = new Random();

    public static Move randomChoice(List<Move> moveListToChoose) {
        return moveListToChoose.get(random.nextInt(moveListToChoose.size()));
    }
}
