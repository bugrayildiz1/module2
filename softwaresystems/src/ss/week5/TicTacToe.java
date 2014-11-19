package ss.week5;

public class TicTacToe {

    public static void main(String[] args) {
        assert args.length >= 2;

        Player playerA = getPlayer(args[0], Mark.XX);
        Player playerB = getPlayer(args[1], Mark.OO);
        Game game = new Game(playerA, playerB);

        game.start();
    }

    private static Player getPlayer(String name, Mark mark) {
        if (name.equals("-N")) {
            return new ComputerPlayer(mark, new NaiveStrategy());
        }

        return new HumanPlayer(name, mark);
    }

}
