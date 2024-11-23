package deque;

public class ArrayDeque<T> implements Deque<T> {
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;

    /** Creates an empty array deque.
     *     0       1      2      3      4      5      6     7
     *     __      __     __     __     __     __     __    __
     * nextFirst  nextLast
     */
    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        nextFirst = 0;
        nextLast = 1;
    }

    private void resize (int capacity) {
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

    /** Adds an item of type T to the front of the deque.
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

    /**  Adds an item of type T to the back of the deque.
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

    /** Returns true if deque is empty, false otherwise.
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /** Returns the number of items in the deque.
     */
    @Override
    public int size() {
        return size;
    }

    /** Removes and returns the item at the front of the deque.
     * If no such item exists, returns null.
     */
    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        T firstItem = items[nextFirst];
        items[nextFirst] = null;
        if (nextFirst == items.length - 1) {
            nextFirst = 0;
        } else {
            nextFirst += 1;
        }
        size -= 1;
        return items[nextFirst];
    }

    /** Removes and returns the item at the back of the deque.
     *  If no such item exists, returns null.
     */
    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        T lastItem = items[nextLast];
        items[nextLast] = null;
        if (nextLast == 0) {
            nextLast = items.length - 1;
        } else {
            nextLast -= 1;
        }
        size -= 1;
        return items[nextLast];
    }

    /** Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
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

    /** Prints the items in the deque from first to last, separated by a space.
     * Once all the items have been printed, print out a new line.
     */
    @Override
    public void printDeque() {
        int current = nextFirst + 1;
        for(int i = 0; i < size; i++) {
            if (current >= items.length) {
                current = 0;
            }
            System.out.print(items[current] + " ");
            current += 1;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        ArrayDeque<String> deque = new ArrayDeque<>();

        deque.addFirst("b");
        deque.addFirst("a");
        deque.addLast("c");
        deque.addLast("d");
        deque.addLast("e");
        deque.addLast("f");
        deque.addLast("g");
        deque.addLast("h");
        deque.addLast("i");

        deque.printDeque();
        System.out.println(deque.get(3));
    }
}
