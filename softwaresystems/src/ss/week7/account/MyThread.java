package ss.week7.account;

public class MyThread extends Thread {
    private final double amount;
    private final int times;
    private final Account account;

    public MyThread(double argAmount, int argTimes, Account argAccount) {
        this.amount = argAmount;
        this.times = argTimes;
        this.account = argAccount;
    }

    @Override
    public void run() {
        for (int i = 0; i < times; i++) {
            account.transaction(amount);
        }
    }
}
