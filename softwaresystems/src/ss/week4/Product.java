package ss.week4;

public class Product implements Function {
    private final Function g;
    private final Function h;

    public Product(Function argG, Function argH) {
        this.g = argG;
        this.h = argH;
    }

    @Override
    public double apply(double x) {
        return g.apply(x) * h.apply(x);
    }

    @Override
    public Function derivative() {
        return new Sum(new Product(g.derivative(), h), new Product(h.derivative(), g));
    }

    @Override
    public String toString() {
        return String.format("%s * %s", g, h);
    }

}
