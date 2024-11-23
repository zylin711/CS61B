package deque;

import java.util.Comparator;
import org.junit.Test;
import static org.junit.Assert.*;

public class MaxArrayDequeTest  {

    // String Comparator
    public static class StringComparator implements Comparator<String> {
        @Override
        public int compare(String a, String b) {
            return a.compareTo(b);
        }
    }

    // Integer Comparator
    public static class IntegerComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer a, Integer b) {
            return a.compareTo(b);
        }
    }

    @Test
    /** Test using the StringComparator */
    public void testStringComparator() {
        MaxArrayDeque<String> deque = new MaxArrayDeque<>(new StringComparator());
        deque.addLast("apple");
        deque.addLast("banana");
        deque.addLast("cherry");
        assertEquals("cherry", deque.max());
    }

    @Test
    public void testIntegerComparator() {
        MaxArrayDeque<Integer> deque = new MaxArrayDeque<>(new IntegerComparator());
        deque.addLast(10);
        deque.addLast(20);
        deque.addLast(5);
        deque.addLast(15);
        assertEquals((Integer) 20, deque.max());
    }

    @Test
    /** Test max on an empty deque */
    public void testEmptyDeque() {
        MaxArrayDeque<String> deque = new MaxArrayDeque<>(new StringComparator());
        assertNull(deque.max());
    }

    @Test
    /** Test max with one element */
    public void testOneElement() {
        MaxArrayDeque<String> deque = new MaxArrayDeque<>(new StringComparator());
        deque.addLast("apple");
        assertEquals("apple", deque.max());
    }

    @Test
    /** Test max with a custom comparator */
    public void testCustomOrder() {
        Comparator<String> customComparator = (a, b) -> a.compareToIgnoreCase(b);
        MaxArrayDeque<String> deque = new MaxArrayDeque<>(new StringComparator());

        deque.addLast("Apple");
        deque.addLast("banana");
        deque.addLast("CHERRY");
        assertEquals("CHERRY", deque.max(customComparator));
    }
}

