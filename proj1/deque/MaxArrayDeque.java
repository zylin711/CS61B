package deque;
import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {
    private Comparator<T> comparator;

    /**
     * creates a MaxArrayDeque with the given Comparator.
     * */
    public MaxArrayDeque(Comparator<T> c) {
        this.comparator = c;
    }

    /**
     * returns the maximum element in the deque as governed by the previously given Comparator.
     * If the MaxArrayDeque is empty, simply return null.
     */
    public T max() {
        if (this.isEmpty()) {
            return null;
        }
        return max(comparator);
    }

    /**
     * returns the maximum element in the deque as governed by the parameter Comparator c.
     * If the MaxArrayDeque is empty, simply return null.
     */
    public T max(Comparator<T> c) {
        if (this.isEmpty()) {
            return null;
        }
        T maxElement = get(0);
        for (int i = 0; i < size(); i++) {
            T currentElement = get(i);
            if (c.compare(currentElement, maxElement) > 0) {
                maxElement = currentElement;
            }
        }
        return maxElement;
    }
}
