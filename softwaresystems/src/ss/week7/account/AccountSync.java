package ss.week7.account;

public class AccountSync {
    public static void main(String[] args) throws InterruptedException {
        Account account = new Account();
        double amount = 5.0d;
        int times = 100;

        Thread thread1 = new MyThread(amount, times, account);
        Thread thread2 = new MyThread(-amount, times, account);

        // Start the threads.
        thread1.start();
        thread2.start();

        // Wait for the threads to complete.
        thread1.join();
        thread2.join();

        // The final balance might or might not be zero as Account isn't thread safe and multiple
        // modifications might occur at the same time which might lead to transactions being lost.
        System.out.printf("Final balance: %f.\n", account.getBalance());
    }
}
