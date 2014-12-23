package ss.week7.threads;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TestSyncConsole extends Thread {
    //private static final Object SYNC_ROOT = new Object();
    private static final Lock LOCK = new ReentrantLock();

    // P-7.12:
    // 1: A ReentrantLock is owned by the thread last successfully locking, but not yet unlocking 
    // it. A thread invoking lock will return, successfully acquiring the lock, when the lock is 
    // not owned by another thread. The method will return immediately if the current thread 
    // already owns the lock.
    // 2: They are essentially the same, but the Reentrantlock is a bit more flexible and is
    // unstructured (unlike synchronized blocks) allowing for code that isn't possible with a
    // single synchronized block (such as locking in one method and unlocking in another).
    // 3: As mentioned in 2, it is unstructured.
    // 4: More code (try-finally construct suggested) which might reduce readability.

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
        //synchronized (SYNC_ROOT) {
        LOCK.lock();
        int num1 = SyncConsole.readInt(String.format("%s: get number 1? ", getName()));
        int num2 = SyncConsole.readInt(String.format("%s: get number 2? ", getName()));
        int sum = num1 + num2;

        SyncConsole.println(String.format("%s: %d + %d = %d", getName(), num1, num2, sum));
        LOCK.unlock();
        //}
    }

    public static void main(String[] args) {
        // P-7.9: Due to the synchronization on the SyncConsole methods this no longer goes FUBAR,
        // but the threads still overlap in asking for numbers.
        new TestSyncConsole("Thread A").start();
        new TestSyncConsole("Thread B").start();
    }
}
