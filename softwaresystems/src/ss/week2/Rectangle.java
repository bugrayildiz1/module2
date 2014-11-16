package ss.week2;

public class Rectangle {
    private final int length;
    private final int width;

    //@ invariant length() > 0;
    //@ invariant width() > 0;

    //@ requires argLength > 0 && argWidth > 0;
    public Rectangle(int argLength, int argWidth) {
        assert argLength > 0 : "argLength > 0";
        assert argWidth > 0 : "argWidth > 0";

        this.length = argLength;
        this.width = argWidth;
    }

    //@ ensures \result > 0;
    /*@ pure */public int length() {
        assert length > 0 : "length > 0";

        return length;
    }

    //@ ensures \result > 0;
    /*@ pure */public int width() {
        assert width > 0 : "width > 0";

        return width;
    }

    //@ ensures \result == length() * width();
    //@ ensures \result > 0;
    public int area() {
        assert width * length > 0 : "area > 0";

        return width * length;
    }

    //@ ensures \result == 2 * (length() + width());
    //@ ensures \result > 0;
    public int perimeter() {
        assert 2 * (width + length) > 0 : "perimeter > 0";

        return 2 * (width + length);
    }
}
