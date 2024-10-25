package IntList;

import static org.junit.Assert.*;
import org.junit.Test;

public class SquarePrimesTest {

    /**
     * Here is a test for isPrime method. Try running it.
     * It passes, but the starter code implementation of isPrime
     * is broken. Write your own JUnit Test to try to uncover the bug!
     */
    @Test
    public void testSquarePrimesSimple() {
        IntList lst = IntList.of(14, 15, 16, 17, 18);
        boolean changed = IntListExercises.squarePrimes(lst);
        assertEquals("14 -> 15 -> 16 -> 289 -> 18", lst.toString());
        assertTrue(changed);
    }

    @Test
    public void testSquarePrime1() {
        IntList lst = IntList.of(16, 17, 18, 19, 20);
        boolean changed = IntListExercises.squarePrimes(lst);
        assertEquals("16 -> 289 -> 18 -> 361 -> 20", lst.toString());
        assertTrue(changed);
    }

    @Test
    public void testSquarePrime2() {
        IntList lst = IntList.of(19, 20, 21, 22, 23, 24, 25);
        boolean changed = IntListExercises.squarePrimes(lst);
        assertEquals("361 -> 20 -> 21 -> 22 -> 529 -> 24 -> 25", lst.toString());
        assertTrue(changed);
    }


}
