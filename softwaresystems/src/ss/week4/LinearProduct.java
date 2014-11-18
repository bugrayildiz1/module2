package ss.week4;

public class LinearProduct extends Product {
    public LinearProduct(Function g, Constant h) {
        super(g, h);
    }

    @Override
    public Function derivative() {
        return new Product(g.derivative(), h);
    }
}
