package deque;

public class LinkedListDeque<T> implements Deque<T>{
    private class Node {
        T item;
        Node prev;
        Node next;

        public Node(T item, Node prev, Node next) {
            this.item = item;
            this.prev = prev;
            this.next = next;
        }
    }

    private Node sentinel;
    private int size;

    /** Create an empty LindeList Deque. */
    public LinkedListDeque() {
        sentinel = new Node(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    /**  Adds an item of type T to the front of the deque.
     * before: sentinel <-> oldFirstNode <-> ...
     * after:  sentinel <-> newItem <-> oldFirstNode <-> ...
     *  @param item: the item to be added in the list.
     */
    @Override
    public void addFirst(T item) {
        Node newItem = new Node(item, sentinel, sentinel.next);
        sentinel.next.prev = newItem;
        sentinel.next = newItem;
        size += 1;
    }

    /** Adds an item of type T to the back of the deque.
     * before: sentinel <-> ... <-> oldLastNode
     * after:  sentinel <-> ... <-> oldFirstNode <-> newItem
     * @param item: the item to be added in the list.
     */
    @Override
    public void addLast(T item) {
        Node newItem = new Node(item, sentinel.prev, sentinel);
        sentinel.prev.next = newItem;
        sentinel.prev = newItem;
        size += 1;
    }
    /** Returns true if deque is empty, false otherwise.
    */
    public boolean isEmpty() {
        return size == 0;
    }

    /** Removes and returns the item at the front of the deque.
     * If no such item exists, returns null.
     * before: sentinel <-> firstNode <-> secondNode <-> ...
     * after:  sentinel <-> secondNode <->  ...
     */
    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        Node removeEle = sentinel.next;
        sentinel.next = removeEle.next;
        sentinel.next.prev = sentinel;
        size -= 1;
        return removeEle.item;
    }

    /** Removes and returns the item at the back of the deque.
     * If no such item exists, returns null.
     * before: sentinel <-> ... <-> secondLastNode <-> lastNode
     * after:  sentinel <-> ... <-> secondLastNode
     */
    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        Node removeEle = sentinel.prev;
        sentinel.prev = removeEle.prev;
        sentinel.prev.next = sentinel;
        size -= 1;
        return removeEle.item;
    }

    @Override
    public int size() {
        return size;
    }

    /** Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
     * Using iteration. If no such item exists, returns null.
     * @param index: the 0-based index of the item to get.
     */
    @Override
    public T get(int index) {
        if (index >= size || index < 0) {
            return null;
        }
        Node p = sentinel.next;
        while (index > 0) {
            p = p.next;
            index -= 1;
        }
        return p.item;
    }

    /** Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
     * Using recursion. If no such item exists, returns null.
     * @param index: the 0-based index of the item to get.
     */
    public T getRecursive(int index) {
        if (index >= size || index < 0) {
            return null;
        }
        return getRecursiveHelper(sentinel.next, index);
    }

    public T getRecursiveHelper(Node node, int index) {
        if (index == 0) {
            return node.item;
        }
        return getRecursiveHelper(node.next, index - 1);
    }


    /** Prints the items in the deque from first to last, separated by a space.
     * Once all the items have been printed, print out a new line.
     */
    @Override
    public void printDeque() {
        Node current = sentinel.next;
        while (current != sentinel) {
            System.out.print(current.item + " ");
            current = current.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        LinkedListDeque<Integer> L = new LinkedListDeque<>();
        L.addFirst(15);
        L.addFirst(10);
        L.addLast(20);
        System.out.println(L.get(1));
        //L.printDeque();
    }


}