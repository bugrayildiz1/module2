package ss.week7.threads;

public class TestSyncConsole extends Thread {
    private static final Object SYNC_ROOT = new Object();

    public TestSyncConsole(String name) {
        super(name);
    }

    @Override
    public void run() {
        sum();
    }

    // P-7.10: There is no difference. A synchronized method will prevent invocations of the method
    // on an object to interleave, but as several objects are created and each object will only
    // invoke sum once it has no effect.
    private synchronized void sum() {
        synchronized (SYNC_ROOT) {
            int num1 = SyncConsole.readInt(String.format("%s: get number 1? ", getName()));
            int num2 = SyncConsole.readInt(String.format("%s: get number 2? ", getName()));
            int sum = num1 + num2;

            SyncConsole.println(String.format("%s: %d + %d = %d", getName(), num1, num2, sum));
        }
    }

    public static void main(String[] args) {
        // P-7.9: Due to the synchronization on the SyncConsole methods this no longer goes FUBAR,
        // but the threads still overlap in asking for numbers.
        new TestSyncConsole("Thread A").start();
        new TestSyncConsole("Thread B").start();
    }
}
