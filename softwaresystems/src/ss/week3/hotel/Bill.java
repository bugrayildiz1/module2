package ss.week3.hotel;

import java.io.PrintStream;

public class Bill {
    private final PrintStream printStream;
    private double sum;

    public Bill(PrintStream argPrintStream) {
        this.printStream = argPrintStream;
        this.sum = 0;
    }

    public void close() {
        print("Sum", sum);
    }

    public double getSum() {
        return sum;
    }

    public void newItem(Item item) {
        sum += item.getAmount();

        print(item.toString(), item.getAmount());
    }

    private void print(String text, double amount) {
        if (printStream != null) {
            printStream.println(String.format("%-20s%6.2f", text, amount));
        }
    }

    public interface Item {
        double getAmount();
    }
}
