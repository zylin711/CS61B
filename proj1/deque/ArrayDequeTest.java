package deque;

import org.junit.Test;
import static org.junit.Assert.*;

public class ArrayDequeTest {

    @Test
    /* Adds a few things to the arraydeque, checking isEmpty() and size() are correct. */
    public void addIsEmptySizeTest() {
        ArrayDeque<String> ad1 = new ArrayDeque<String>();

        assertTrue("A newly initialized ArrayDeque should be empty", ad1.isEmpty());

        ad1.addFirst("front");
        assertEquals(1, ad1.size());
        assertFalse("ad1 should now contain 1 item", ad1.isEmpty());

        ad1.addLast("middle");
        assertEquals(2, ad1.size());
        ad1.addLast("back");
        assertEquals(3,ad1.size());

        System.out.println("Printing out deque:");
        ad1.printDeque();
    }

    @Test
    /* Adds an item, then removes an item, and ensures that arraydeque is empty afterwards. */
    public void addRemoveTest() {
        ArrayDeque<Integer> ad1 = new ArrayDeque<Integer>();
        assertTrue("ad1 should be empty upon initialization", ad1.isEmpty());

        ad1.addFirst(10);
        assertFalse("ad1 should contain 1 item", ad1.isEmpty());

        ad1.removeFirst();
        assertTrue("ad1 should be empty after removal", ad1.isEmpty());
    }


    @Test
    /* Tests removing from an empty arraydeque. */
    public void removeEmptyTest() {
        ArrayDeque<String> ad1 = new ArrayDeque<String>();

        assertTrue("ad1 should be empty upon initialization", ad1.isEmpty());
        assertNull("removeFirst should return null when ad1 is empty", ad1.removeFirst());
        assertNull("removeLast should return null when ad1 is empty", ad1.removeLast());

        ad1.addFirst("only");
        assertEquals("removeLast should return 'only'", "only", ad1.removeLast());
        assertNull("removeFirst should return null after removing the only element", ad1.removeFirst());
    }


    @Test
    /* Check if you can create arraydeques with different parameterized types. */
    public void multipleParamTest() {
        ArrayDeque<String> ad1 = new ArrayDeque<String>();
        ArrayDeque<Double> ad2 = new ArrayDeque<Double>();
        ArrayDeque<Boolean> ad3 = new ArrayDeque<Boolean>();

        ad1.addFirst("string");
        ad2.addFirst(3.14159);
        ad3.addFirst(true);

        String s = ad1.removeFirst();
        double d = ad2.removeFirst();
        boolean b = ad3.removeFirst();
    }


    @Test
    /* Add large number of elements to arraydeque; check if order is correct. */
    public void bigLLDequeTest() {
        ArrayDeque<Integer> ad1 = new ArrayDeque<Integer>();
        int N = 1000000;
        for (int i = 0; i < N; i++) {
            ad1.addLast(i);
        }
        assertEquals("Size should be" + N, N, ad1.size());
    }


    @Test
    /* Check the resize functionality by adding and removing elements to trigger resizing. */
    public void testResize() {
        ArrayDeque<Integer> ad1 = new ArrayDeque<Integer>();
        int initialCapacity = 8;

        for (int i = 0; i < initialCapacity; i++) {
            ad1.addLast(i);
        }
        assertEquals("Size should be " + initialCapacity, initialCapacity, ad1.size());

        ad1.addLast(initialCapacity);
        assertEquals("Size should be " + (initialCapacity + 1), initialCapacity + 1, ad1.size());

        for (int i = 0; i < 3; i ++) {
            Integer removed = ad1.removeFirst();
        }
        assertEquals(initialCapacity - 2, ad1.size());
    }

}
