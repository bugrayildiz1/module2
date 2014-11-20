package ss.week5;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SmartStrategy implements Strategy {

    @Override
    public String getName() {
        return "Smart";
    }

    @Override
    public int determineMove(Board b, Mark m) {
        // If the middle field is free, always take it.
        if (b.isEmptyField(Board.DIM / 2, Board.DIM / 2)) {
            return b.index(Board.DIM / 2, Board.DIM / 2);
        }

        // Get all fields that are currently free.
        List<Integer> freeFields = getFreeFields(b);

        // For each free field:
        // Create a copy of the board.
        // Set the free field to the strategies mark.
        // If this results to the board having a winner this is a winning move for the strategy so
        // return that field.
        for (int field : freeFields) {
            Board board = b.deepCopy();

            board.setField(field, m);

            if (board.hasWinner()) {
                return field;
            }
        }

        // Could not make a winning move so now try to prevent the opponent from making a winning
        // move. First get the mark of the opponent.
        Mark other = m == Mark.XX ? Mark.OO : Mark.XX;

        // For each free field:
        // Create a copy of the board.
        // Set the free field to the opponents mark.
        // If this results to the board having a winner, setting that field to the strategies mark
        // prevents a winning move for the opponent so return that field.
        for (int field : freeFields) {
            Board board = b.deepCopy();

            board.setField(field, other);

            if (board.hasWinner()) {
                return field;
            }
        }

        // Could not prevent a winning move either so simply select a random free field now.
        Random random = new Random();
        return freeFields.get(random.nextInt(freeFields.size()));
    }

    private static List<Integer> getFreeFields(Board b) {
        List<Integer> freeFields = new ArrayList<Integer>();

        for (int i = 0; i < Board.DIM * Board.DIM; i++) {
            if (b.isEmptyField(i)) {
                freeFields.add(i);
            }
        }

        return freeFields;
    }

}
