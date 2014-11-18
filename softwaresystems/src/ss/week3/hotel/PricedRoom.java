package ss.week3.hotel;

public class PricedRoom extends Room implements Bill.Item {
    private final double price;

    public PricedRoom(int no, double roomPrice, double safePrice) {
        super(no, new PricedSafe(safePrice));

        this.price = roomPrice;
    }

    @Override
    public double getAmount() {
        return price;
    }

    @Override
    public String toString() {
        return String.format("Room (%.2f per night)", price);
    }

}
