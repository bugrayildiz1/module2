package ss.week5;

public class TicTacToe {

    public static void main(String[] args) {
        assert args.length >= 2;

        Player playerA = new HumanPlayer(args[0], Mark.OO);
        Player playerB = new HumanPlayer(args[1], Mark.XX);
        Game game = new Game(playerA, playerB);

        game.start();
    }

}
