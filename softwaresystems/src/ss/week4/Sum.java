package ss.week4;

public class Sum implements Function, Intergrandable {
    private final Function g;
    private final Function h;

    public Sum(Function argG, Function argH) {
        this.g = argG;
        this.h = argH;
    }

    @Override
    public double apply(double x) {
        return g.apply(x) + h.apply(x);
    }

    @Override
    public Function derivative() {
        return new Sum(g.derivative(), h.derivative());
    }

    @Override
    public String toString() {
        return String.format("%s + %s", g, h);
    }

    @Override
    public Function integrand() {
        if (!(g instanceof Intergrandable)) {
            throw new TypeNotPresentException("ss.week4.Integrandable", null);
        }
        if (!(h instanceof Intergrandable)) {
            throw new TypeNotPresentException("ss.week4.Integrandable", null);
        }

        return new Sum(((Intergrandable) g).integrand(), ((Intergrandable) h).integrand());
    }
}
