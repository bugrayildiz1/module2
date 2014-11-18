package ss.week4;

public class Constant implements Function, Intergrandable {
    private final double value;

    public Constant(double argValue) {
        this.value = argValue;
    }

    @Override
    public double apply(double x) {
        return value;
    }

    @Override
    public Function derivative() {
        return new Constant(0);
    }

    @Override
    public String toString() {
        return String.format("%f", value);
    }

    @Override
    public Function integrand() {
        return new Product(new Constant(value), new Exponent(1));
    }

}
