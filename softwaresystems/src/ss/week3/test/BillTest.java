package ss.week3.test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import ss.week3.hotel.BasicBillItem;
import ss.week3.hotel.Bill;

public class BillTest {
    private static final double DELTA = 0.0001;
    private final BasicBillItem item1 = new BasicBillItem("item1", 13.37);
    private final BasicBillItem item2 = new BasicBillItem("item2", 5.0);
    private Bill bill;

    @Before
    public void setUp() throws Exception {
        bill = new Bill(null);
    }

    @Test
    public void testBill() {
        assertEquals("initial bill", 0.0, bill.getSum(), DELTA);

        bill.newItem(item1);

        assertEquals("after item1", 13.37, bill.getSum(), DELTA);

        bill.newItem(item2);

        assertEquals("after item2", 18.37, bill.getSum(), DELTA);

        bill.newItem(item1);
        bill.newItem(item2);

        assertEquals("after item1+2", 36.74, bill.getSum(), DELTA);

        bill.close();

        assertEquals("after close", 36.74, bill.getSum(), DELTA);

    }

}
