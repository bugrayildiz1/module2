package ss.week4;

public class LinearProduct extends Product {
    public LinearProduct(Constant g, Function h) {
        super(g, h);
    }

    @Override
    public Function derivative() {
        return new Product(g, h.derivative());
    }
}
