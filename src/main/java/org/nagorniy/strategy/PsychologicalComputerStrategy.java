package org.nagorniy.strategy;

import org.nagorniy.model.GameResult;
import org.nagorniy.model.GameRound;
import org.nagorniy.model.Move;
import org.nagorniy.utils.GameResultDeterminer;
import org.nagorniy.utils.RandomMoveSelector;

import java.util.List;
import java.util.stream.Collectors;

public class PsychologicalComputerStrategy implements ComputerStrategy {

    /**
     * This strategy makes decision about the next computer move based on psychological hypothesis.
     * <p>
     * The algorithm finds the way to counter the user.
     * <p>
     * <p>
     * If the result of previous round is {@code GameResult.COMPUTER_WIN}:
     * our hypothesis is that the user assumes that the computer doesn't change its choice and will repeat its winning move.
     * In this case, the computer chooses the move to counter the user.
     * <p>
     * If the result of previous round is {@code GameResult.USER_WIN}:
     * our hypothesis is that the user assumes that the computer will seek
     * to take revenge (provided that the user doesn't change his choice and will repeat his winning move).
     * In this case, our algorithm will choose the move for a double counter.
     * <p>
     * In the case of a {@code GameResult.DRAW}:
     * the algorithm assumes that the user will change his choice to not play a boring draws.
     * In turn, the algorithm will randomly select a move from the set that
     * doesn't contain a winning move to the previous round (we assume that the user will not choose it).
     * Thus, we increase the probability of win.
     * <p>
     * If the game history is empty(no games played -
     * algorithm'll choose random move from the all possible move values).
     *
     * @param gameHistory list with results of previous rounds.
     * @return next computer {@code Move}
     */
    @Override
    public Move defineNextChoice(List<GameRound> gameHistory) {

        if (gameHistory == null || gameHistory.size() == 0) {
            return RandomMoveSelector.randomChoice(MOVE_VALUES);
        } else {

            GameRound lastGame = gameHistory.get(gameHistory.size() - 1);

            Move nextComputerMove;

            // if computer won - user will choose move to beat the previous computer's move
            if (GameResult.COMPUTER_WIN.equals(lastGame.getGameResult())) {
                Move nextUserMove = GameResultDeterminer.getWinnerForMove(lastGame.getComputerMove());

                nextComputerMove = GameResultDeterminer.getWinnerForMove(nextUserMove);

                // if user won - user guess that computer will change move to beat the user's previous choice,
                // so computer changes his move to beat changed user's move
            } else if (GameResult.USER_WIN.equals(lastGame.getGameResult())) {
                Move predictedByUserNextComputerMove = GameResultDeterminer.getWinnerForMove(lastGame.getUserMove());
                Move nextUserMove = GameResultDeterminer.getWinnerForMove(predictedByUserNextComputerMove);
                nextComputerMove = GameResultDeterminer.getWinnerForMove(nextUserMove);

                // if draw - we assumed that user will change its move,
                // so there will be low chance to win if computer choose the winner for previous user's move.
                // To increase the probability of win from 33.33% up to 50%,
                // computer will choose one move from the list of other two moves
            } else {
                nextComputerMove = RandomMoveSelector
                        .randomChoice(MOVE_VALUES
                                .stream()
                                .filter(move -> !move.equals(lastGame.getUserMove()))
                                .collect(Collectors.toList()));
            }
            return nextComputerMove;
        }
    }
}
