package ss.week4.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import ss.week4.Constant;
import ss.week4.Exponent;
import ss.week4.LinearProduct;
import ss.week4.Polynomial;
import ss.week4.Product;
import ss.week4.Sum;

public class FunctionTests {
    private static final double DELTA = 0.001;

    @Test
    public void testConstant() {
        Constant constant = new Constant(5);

        assertEquals(5, constant.apply(0), DELTA);
    }

    @Test
    public void testExponent() {
        Exponent exponent = new Exponent(3);

        assertEquals(27, exponent.apply(3), DELTA);
    }

    @Test
    public void testLinearProduct() {
        Constant constant = new Constant(3);
        Exponent exponent = new Exponent(2);
        LinearProduct linearProduct = new LinearProduct(constant, exponent);

        assertEquals(27, linearProduct.apply(3), DELTA);
    }

    @Test
    public void testPolynomial() {
        Polynomial polynomial = new Polynomial(1, 2, 3);

        assertEquals(17, polynomial.apply(2), DELTA);
    }

    @Test
    public void testProduct() {
        Constant constant = new Constant(4);
        Exponent exponent = new Exponent(2);
        Product product = new Product(constant, exponent);

        assertEquals(36, product.apply(3), DELTA);
    }

    @Test
    public void testSum() {
        Constant constant = new Constant(4);
        Exponent exponent = new Exponent(2);
        Sum sum = new Sum(constant, exponent);

        assertEquals(13, sum.apply(3), DELTA);
    }

}
