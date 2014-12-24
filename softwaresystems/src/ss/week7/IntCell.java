package ss.week7;

public class IntCell {
    private int contents = 0;

    public void add(int amount) {
        contents = contents + amount;
    }

    public int get() {
        return contents;
    }

    public static void main(String[] args) {
        IntCell cell = new IntCell();
        Adder a1 = new Adder(cell, 1);
        Adder a2 = new Adder(cell, 2);
        a1.start();
        a2.start();
        try {
            a1.join();
            a2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(cell.get());
        // P-7.18.1: The possible outcomes are 1,2 & 3. The threads will either update the value
        // one after another, or they will overlap and one thread will overwrite the value of the
        // other (causing the value 1 or 2).
        // P-7.18.2: The result would always be 3 as they would be executed sequentially on the
        // calling thread.
        // P-7.18.3: The result would be identical to 18.1 with the added case of 0 if neither
        // thread has updated the amount in the IntCell before the sysout call.
        // P-7.18.4: As the threads can no longer overlap (as their execution would block on the
        // synchronized method call) they are performed sequentially, since the order of addition
        // does not matter the result will always be 3.
    }
}

class Adder extends Thread {
    private static final Object SYNC = new Object();

    private IntCell cell;
    private int amount;

    public Adder(IntCell cellArg, int amountArg) {
        this.cell = cellArg;
        this.amount = amountArg;
    }

    public void run() {
        // Only allow one call to cell.add at a time across every Adder instance.
        synchronized (SYNC) {
            cell.add(amount);
        }
    }
}
