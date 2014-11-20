package ss.week5;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NaiveStrategy implements Strategy {

    @Override
    public String getName() {
        return "Naive";
    }

    @Override
    public int determineMove(Board b, Mark m) {
        List<Integer> freeSlots = new ArrayList<Integer>();
        Random random = new Random();

        for (int i = 0; i < Board.DIM * Board.DIM; i++) {
            if (b.isEmptyField(i)) {
                freeSlots.add(i);
            }
        }

        return freeSlots.get(random.nextInt(freeSlots.size()));
    }
}
