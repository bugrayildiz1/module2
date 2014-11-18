package ss.week4;

public class Exponent implements Function {
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
        return new LinearProduct(new Exponent(n - 1), new Constant(n));
    }

    @Override
    public String toString() {
        return String.format("x^%d", n);
    }
}
