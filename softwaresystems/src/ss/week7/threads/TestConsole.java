package ss.week7.threads;

public class TestConsole extends Thread {
    public TestConsole(String name) {
        super(name);
    }

    @Override
    public void run() {
        sum();
    }

    private void sum() {
        int num1 = Console.readInt(String.format("%s: get number 1? ", getName()));
        int num2 = Console.readInt(String.format("%s: get number 2? ", getName()));
        int sum = num1 + num2;

        Console.println(String.format("%s: %d + %d = %d", getName(), num1, num2, sum));
    }

    public static void main(String[] args) {
        // P-7.7: This goes FUBAR as several threads try to read from stdin at the same time, which
        // clearly doesn't work without some kind of synchronization.
        new TestConsole("Thread A").start();
        new TestConsole("Thread B").start();
    }
}
