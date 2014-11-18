package ss.week4;

public class Polynomial implements Function, Intergrandable {
    private final LinearProduct[] products;

    public Polynomial(double... argA) {
        this.products = new LinearProduct[argA.length];

        for (int i = 0; i < argA.length; i++) {
            int n = argA.length - i - 1;
            double an = argA[n];

            products[i] = new LinearProduct(new Constant(an), new Exponent(n));
        }
    }

    private Polynomial(LinearProduct[] argProducts) {
        this.products = argProducts;
    }

    @Override
    public double apply(double x) {
        double result = 0;

        for (LinearProduct product : products) {
            result += product.apply(x);
        }

        return result;
    }

    @Override
    public Function derivative() {
        LinearProduct[] newProducts = new LinearProduct[products.length];

        for (int i = 0; i < newProducts.length; i++) {
            newProducts[i] = (LinearProduct) products[i].derivative();
        }

        return new Polynomial(newProducts);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        for (LinearProduct product : products) {
            builder.append(product.toString());
            builder.append(" + ");
        }

        return builder.substring(0, builder.length() - 3);
    }

    @Override
    public Function integrand() {
        LinearProduct[] newProducts = new LinearProduct[products.length];

        for (int i = 0; i < newProducts.length; i++) {
            newProducts[i] = (LinearProduct) products[i].integrand();
        }

        return new Polynomial(newProducts);
    }
}
