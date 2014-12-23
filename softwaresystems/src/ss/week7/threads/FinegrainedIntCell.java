package ss.week7.threads;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class FinegrainedIntCell implements IntCell {
    private final Lock lock = new ReentrantLock();
    private final Condition conConsumed = lock.newCondition();
    private final Condition conProduced = lock.newCondition();
    private int value;
    private boolean consumed = true;

    public FinegrainedIntCell() {
        this.value = 0;
    }

    @Override
    public void setValue(int val) {
        lock.lock();
        if (!consumed) {
            try {
                conConsumed.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        value = val;
        conProduced.signal();
        consumed = false;
        lock.unlock();
    }

    @Override
    public int getValue() {
        lock.lock();
        if (consumed) {
            try {
                conProduced.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        int val = value;
        consumed = true;
        conConsumed.signal();
        lock.unlock();

        return val;
    }

}
