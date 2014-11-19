package ss.week5;

public class ComputerPlayer extends Player {
    private Strategy strategy;

    public ComputerPlayer(Mark mark) {
        this(mark, new NaiveStrategy());
    }

    public ComputerPlayer(Mark mark, Strategy argStrategy) {
        super(String.format("%s-%s", argStrategy.getName(), mark), mark);

        this.strategy = argStrategy;
    }

    @Override
    public int determineMove(Board board) {
        return strategy.determineMove(board, getMark());
    }

    public Strategy getStrategy() {
        return strategy;
    }

    public void setStrategy(Strategy argStrategy) {
        strategy = argStrategy;
    }
}
