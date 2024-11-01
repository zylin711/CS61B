package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by hug.
 */

public class TestBuggyAList {
    @Test
    public void testThreeAddThreeRemove() {
        AListNoResizing<Integer> correct = new AListNoResizing<>();
        BuggyAList<Integer> buggy = new BuggyAList<>();

        for (int i = 4; i < 7; i ++ ) {
            correct.addLast(i);
            buggy.addLast(i);
        }

        assertEquals(correct.size(), buggy.size());
        for (int j = 0; j < 3; j ++) {
            assertEquals(correct.removeLast(), buggy.removeLast());
        }
    }

    @Test
    public void randomizedTest() {
        AListNoResizing<Integer> correct = new AListNoResizing<>();
        BuggyAList<Integer> buggy = new BuggyAList<>();

        int N = 5000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 4);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                correct.addLast(randVal);
                buggy.addLast(randVal);
                System.out.println("addLast(" + randVal + ")");

            } else if (operationNumber == 1) {
                // size
                System.out.println("correct size: " + correct.size());
                System.out.println("buggy size: " + correct.size());
                assertEquals(correct.size(), buggy.size());

            } else if (operationNumber == 2 && correct.size() > 0) {
                // getLast
                int correct_lastEle = correct.getLast();
                int buggy_lastEle = buggy.getLast();
                System.out.println("correct last number: " + correct_lastEle);
                System.out.println("buggy last number: " + buggy_lastEle);
                assertEquals(correct_lastEle, buggy_lastEle);

            } else if (operationNumber == 3 && correct.size() > 0) {
                // removeLase
                int correct_lastEle = correct.removeLast();
                int buggy_lastEle = buggy.removeLast();
                assertEquals(correct_lastEle, buggy_lastEle);
                System.out.println("correct remove last number: " + correct_lastEle);
                System.out.println("correct remove last number: " + buggy_lastEle);
            }

            }
        }
    }
