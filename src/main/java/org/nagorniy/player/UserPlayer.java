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

    /**
     * This map is used to validate user's input and convert it to the {@code Move}
     */
    private final Map<String, Move> moveMappingMap = Collections.unmodifiableMap(
            new HashMap<String, Move>() {{
                put("R", Move.ROCK);
                put("P", Move.PAPER);
                put("S", Move.SCISSORS);
            }});

    public UserPlayer(Scanner scanner) {
        this.scanner = scanner;
    }

    /**
     * Method to ask user for the new move.
     * The first letter of user's input will be converted to {@code Move} by {@code moveMappingMap}.
     * This method is not case-sensitive.
     * In case of incorrect input - {@code INVALID_INPUT_MESSAGE} will be printed and retry invoked
     *
     * @return user's move
     */
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
