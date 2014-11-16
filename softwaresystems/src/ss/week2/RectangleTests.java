package ss.week2;

import ss.utils.TestRunner;

public class RectangleTests extends TestRunner {

    public static void main(String[] args) {
        new RectangleTests().runTests();
    }

    public RectangleTests() {
        super("Rectangle");
    }

    @TestMethod
    public void testConstructor() {
        Rectangle rect = new Rectangle(2, 3);

        testEqual(rect.length(), 2, "Constructor length");
        testEqual(rect.width(), 3, "Constructor width");
    }

    @TestMethod
    public void testArea() {
        Rectangle rect = new Rectangle(4, 5);

        testEqual(rect.area(), 20, "Area");
    }

    @TestMethod
    public void testPerimeter() {
        Rectangle rect = new Rectangle(2, 4);

        testEqual(rect.perimeter(), 12, "Perimeter");
    }
}
