package ss.week4;

public interface Intergrandable {
    // The reason behind creating a separate interface is that not all functions have a predefined
    // integrand. You could opt to return null on those cases, but that would be very ugly. Having
    // a seperate interface also allows for easy checking if a function has an integrand.

    Function integrand();
}
