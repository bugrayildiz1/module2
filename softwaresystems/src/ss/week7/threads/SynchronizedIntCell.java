package ss.week7.threads;

public class SynchronizedIntCell implements IntCell {
    private boolean consumed;
    private int value;

    public SynchronizedIntCell() {
        this.value = 0;
        this.consumed = true;
    }

    @Override
    public synchronized void setValue(int val) {
        if (!consumed) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        value = val;
        consumed = false;
        this.notify();
    }

    @Override
    public synchronized int getValue() {
        if (consumed) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        consumed = true;
        int val = value;
        this.notify();

        return val;
    }

}
