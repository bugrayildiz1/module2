package ss.week3.hotel;

import ss.week3.hotel.Bill.Item;

/**
 * Representation of a Fridge in a hotel room.
 *
 * @author Maarten Everts
 */
public class Fridge implements Item {
    final static int PRICE_PER_DRINK = 2;
    private int numberOfDrinks = 0;

    /**
     * Construct a Fridge with a certain number of drinks used.
     * @param argNumberOfDrinks number of drinks used from the fridge.
     */
    public Fridge(int argNumberOfDrinks) {
        this.numberOfDrinks = argNumberOfDrinks;
    }

    /**
     * Set the number of drinks used from the fridge.
     * @param argNumberOfDrinks
     */
    public void setNumberOfDrinks(int argNumberOfDrinks) {
        this.numberOfDrinks = argNumberOfDrinks;
    }

    /**
     * Return the current number of drinks used from the fridge.
     * @return current number of drinks used from the fridge.
     */
    public int getNumberOfDrinks() {
        return numberOfDrinks;
    }

    /**
     * Returns the total cost of the items used from the fridge.
     */
    @Override
    public double getAmount() {
        return (double) (numberOfDrinks * PRICE_PER_DRINK);
    }

}
