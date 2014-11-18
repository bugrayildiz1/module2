package ss.week4;

public class Exponent implements Function, Intergrandable {
    private final int n;

    public Exponent(int argN) {
        this.n = argN;
    }

    @Override
    public double apply(double x) {
        return Math.pow(x, n);
    }

    @Override
    public Function derivative() {
        return new LinearProduct(new Constant(n), new Exponent(n - 1));
    }

    @Override
    public String toString() {
        return String.format("x^%d", n);
    }

    @Override
    public Function integrand() {
        return new LinearProduct(new Constant(1 / (n + 1)), new Exponent(n + 1));
    }
}
