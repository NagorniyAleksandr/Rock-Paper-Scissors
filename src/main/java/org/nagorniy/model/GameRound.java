package org.nagorniy.model;

import java.util.Objects;

public class GameRound {

    private Move userMove;
    private Move computerMove;
    private GameResult gameResult;

    public GameRound(Move userMove, Move computerMove, GameResult gameResult) {
        this.userMove = userMove;
        this.computerMove = computerMove;
        this.gameResult = gameResult;
    }

    public Move getUserMove() {
        return userMove;
    }

    public GameRound setUserMove(Move userMove) {
        this.userMove = userMove;
        return this;
    }

    public Move getComputerMove() {
        return computerMove;
    }

    public GameRound setComputerMove(Move computerMove) {
        this.computerMove = computerMove;
        return this;
    }

    public GameResult getGameResult() {
        return gameResult;
    }

    public GameRound setGameResult(GameResult gameResult) {
        this.gameResult = gameResult;
        return this;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("GameRound{");
        sb.append("userMove=").append(userMove);
        sb.append(", computerMove=").append(computerMove);
        sb.append(", gameResult=").append(gameResult);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameRound gameRound = (GameRound) o;
        return userMove == gameRound.userMove &&
                computerMove == gameRound.computerMove &&
                gameResult == gameRound.gameResult;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userMove, computerMove, gameResult);
    }
}
