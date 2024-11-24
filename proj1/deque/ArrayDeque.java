package deque;
import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T>, Iterable<T> {
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;

    /**
     * Creates an empty array deque.
     * 0       1      2      3      4      5      6     7
     * __      __     __     __     __     __     __    __
     * nextFirst  nextLast
     */
    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        nextFirst = 0;
        nextLast = 1;
    }

    private void resize(int capacity) {
        T[] newItems = (T[]) new Object[capacity];
        int oldIndex = nextFirst + 1;
        for (int i = 0; i < size; i++) {
            if (oldIndex >= items.length) {
                oldIndex = 0;
            }
            newItems[i] = items[oldIndex];
            oldIndex += 1;
        }
        items = newItems;
        nextFirst = capacity - 1;
        nextLast = size;
    }

    /**
     * For arrays of length 16 or more,
     * your usage factor should be at least 25%.
     */

    private void checkAndShrink() {
        if (items.length > 16 && size < items.length / 4) {
            resize(items.length / 2);
        }
    }

    /**
     * Adds an item of type T to the front of the deque.
     * @param item: the item to be added in the array.
     */
    @Override
    public void addFirst(T item) {
        if (size == items.length) {
            resize(size * 2);
        }
        items[nextFirst] = item;
        if (nextFirst == 0) {
            nextFirst = items.length - 1;
        } else {
            nextFirst -= 1;
        }
        size += 1;
    }

    /**
     * Adds an item of type T to the back of the deque.
     * @param item: the item to be added in the array.
     */
    @Override
    public void addLast(T item) {
        if (size == items.length) {
            resize(size * 2);
        }
        items[nextLast] = item;
        if (nextLast == items.length - 1) {
            nextLast = 0;
        } else {
            nextLast += 1;
        }
        size += 1;
    }

    /**
     * Returns the number of items in the deque.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Removes and returns the item at the front of the deque.
     * If no such item exists, returns null.
     */
    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        if (nextFirst == items.length - 1) {
            nextFirst = 0;
        } else {
            nextFirst += 1;
        }
        T firstItem = items[nextFirst];
        items[nextFirst] = null;
        size -= 1;
        checkAndShrink();
        return firstItem;
    }

    /**
     * Removes and returns the item at the back of the deque.
     * If no such item exists, returns null.
     */
    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        if (nextLast == 0) {
            nextLast = items.length - 1;
        } else {
            nextLast -= 1;
        }
        T lastItem = items[nextLast];
        items[nextLast] = null;
        size -= 1;
        checkAndShrink();
        return lastItem;
    }

    /**
     * Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
     * If no such item exists, returns null.
     */
    @Override
    public T get(int index) {
        if (index >= size) {
            return null;
        }
        int realIndex = index + (nextFirst + 1);
        if (realIndex >= items.length) {
            realIndex -= items.length;
        }
        return items[realIndex];
    }

    /**
     * Prints the items in the deque from first to last, separated by a space.
     * Once all the items have been printed, print out a new line.
     */
    @Override
    public void printDeque() {
        int current = nextFirst + 1;
        for (int i = 0; i < size; i++) {
            if (current >= items.length) {
                current = 0;
            }
            System.out.print(items[current] + " ");
            current += 1;
        }
        System.out.println();
    }

    /**
     * Returns an iterator for the deque.
     */
    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }

    private class ArrayDequeIterator implements Iterator<T> {
        private int index;

        ArrayDequeIterator() {
            index = 0;
        }

        public boolean hasNext() {
            return index < size;
        }

        public T next() {
            T item = get(index);
            index += 1;
            return item;
        }
    }

    /**
     * Returns whether or not the parameter o is equal to the Deque.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof Deque)) {
            return false;
        }
        Deque<T> other = (Deque<T>) obj;
        if (this.size() != other.size()) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (!(this.get(i).equals(other.get(i)))) {
                return false;
            }
        }
        return true;
    }
}
