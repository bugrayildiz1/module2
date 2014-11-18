package ss.week3.hotel;

public class BasicBillItem implements Bill.Item {
    private final String text;
    private final double amount;

    public BasicBillItem(String argText, double argAmount) {
        this.text = argText;
        this.amount = argAmount;
    }

    @Override
    public double getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return text;
    }
}
