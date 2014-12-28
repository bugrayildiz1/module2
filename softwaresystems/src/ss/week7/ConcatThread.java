package ss.week7;

public class ConcatThread extends Thread {
    private static String text = ""; // global variable
    private String toe;

    public ConcatThread(String toeArg) {
        this.toe = toeArg;
    }

    public void run() {
        // P-7.19.3: Force sequential executing of the thread code by locking on a static variable.
        synchronized (text) {
            text = text.concat(toe);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread a = new ConcatThread("one;");
        Thread b = new ConcatThread("two;");

        a.start();
        b.start();

        a.join();
        b.join();

        // P-7.19.2: Possible outcomes are 'one;two;' or 'two;one;' depending on which thread is
        // executed first and assuming they are executed sequentially. If they execute interleaved
        // it is possible the 'result' of one thread (assigning to text) is overruled by the other
        // thread in which case the outcome can be either 'one;' or 'two;'.
        System.out.println(text);
    }
}
