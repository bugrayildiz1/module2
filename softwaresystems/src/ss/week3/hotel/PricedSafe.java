package ss.week3.hotel;

public class PricedSafe extends Safe implements Bill.Item {
    private final double price;

    public PricedSafe(double argPrice) {
        super(new Password());

        this.price = argPrice;
    }

    @Override
    public double getAmount() {
        return price;
    }

    @Override
    public String toString() {
        return String.format("Priced safe (%.2f)", price);
    }

}
