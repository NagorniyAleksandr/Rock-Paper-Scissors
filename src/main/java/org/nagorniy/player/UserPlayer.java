package org.nagorniy.player;

import org.nagorniy.model.Move;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class UserPlayer implements Player {

    private static final String INVALID_INPUT_MESSAGE = "Invalid input, try again...";
    private static final String ASK_USER_FOR_CHOICE_MESSAGE =
            "Make your choice: Rock(R), Paper(P), or Scissors(S): ";

    private final Scanner scanner;
    private final Map<String, Move> moveMappingMap = Collections.unmodifiableMap(
            new HashMap<String, Move>() {{
                put("R", Move.ROCK);
                put("P", Move.PAPER);
                put("S", Move.SCISSORS);
            }});

    public UserPlayer(Scanner scanner) {
        this.scanner = scanner;
    }

    public Move makeMove() {

        System.out.println(ASK_USER_FOR_CHOICE_MESSAGE);

        String userInput = scanner.next();
        userInput = userInput.toUpperCase();
        if (moveMappingMap.containsKey(userInput)) {
            return moveMappingMap.get(userInput);
        } else {
            System.out.println(INVALID_INPUT_MESSAGE);
            return makeMove();
        }
    }
}
