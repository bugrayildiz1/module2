package ss.week4;

public class LinearProduct extends Product implements Intergrandable {
    public LinearProduct(Constant g, Function h) {
        super(g, h);
    }

    @Override
    public Function derivative() {
        return new Product(g, h.derivative());
    }

    @Override
    public Function integrand() {
        if (!(h instanceof Intergrandable)) {
            throw new TypeNotPresentException("ss.week4.Intergrandable", null);
        }

        return new LinearProduct((Constant) g, ((Intergrandable) h).integrand());
    }
}
