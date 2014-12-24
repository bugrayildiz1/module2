package ss.week7.account;

public class Account {
    private static final double MIN = -1000.0d;
    protected double balance = 0.0;

    public void transaction(double amount) {
        while (balance + amount < MIN) {
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        synchronized (this) {
            balance = balance + amount;
        }
    }

    public double getBalance() {
        return balance;

    }
}
