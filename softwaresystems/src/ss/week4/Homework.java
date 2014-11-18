package ss.week4;

public class Homework {

    public static void main(String[] args) {
        LinearProduct f1 = new LinearProduct(new Constant(4), new Exponent(4));
        Function f2 = f1.integrand();
        Function f3 = f1.derivative();

        print(f1, 8);
        print(f2, 8);
        print(f3, 8);
    }

    private static void print(Function function, double x) {
        System.out
                .println(String.format("f(x) = %s, f(%f) =  %.1f", function, x, function.apply(x)));
    }
}
