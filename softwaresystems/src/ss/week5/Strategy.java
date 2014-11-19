package ss.week5;

public interface Strategy {
    String getName();

    int determineMove(Board b, Mark m);
}
