package org.nagorniy;

import org.nagorniy.service.Game;
import org.nagorniy.service.RockPaperScissorsGame;

import java.util.Random;
import java.util.Scanner;

public class Application {

    public static void main(String[] args) {
        Game game = new RockPaperScissorsGame(new Scanner(System.in), new Random());
        game.playGame();
    }
}
