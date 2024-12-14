package bstmap;

import edu.princeton.cs.algs4.StdOut;
import java.util.*;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V>  {
    private BSTNode root;
    private int size = 0;

    /* Inner class representing a node in the BST. */
    private class BSTNode {
        private K key;
        private V value;
        private BSTNode left;
        private BSTNode right;

        public BSTNode(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    /* Clears the map by setting the root to null and resetting the size. */
    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    /* Checks if the map contains the given key. */
    @Override
    public boolean containsKey(K key) {
        return containsKeyHelper(root, key);
    }

    private boolean containsKeyHelper(BSTNode node, K key) {
        if (node == null) {
            return false;
        }
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            return containsKeyHelper(node.left, key);
        } else if (cmp > 0) {
            return containsKeyHelper(node.right, key);
        } else {
            return true;
        }
    }

    /* Retrieves the value associated with the given key. */
    @Override
    public V get(K key) {
        if (key == null) {
            return null;
        }
        return getHelper(root, key);
    }

    private V getHelper(BSTNode node, K key) {
        if (node == null) {
            return null;
        }
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            return getHelper(node.left, key);
        } else if (cmp > 0) {
            return getHelper(node.right, key);
        } else {
            return node.value;
        }
    }

    /* Returns the size of the map. */
    @Override
    public int size() {
        return size;
    }

    /* Inserts a key-value pair into the map. */
    @Override
    public void put(K key, V value) {
        root = putHelper(root, key, value);
        size += 1;
    }

    private BSTNode putHelper(BSTNode node, K key, V value) {
        if (node == null) {
            return new BSTNode(key, value);
        }
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = putHelper(node.left, key, value);
        } else if (cmp > 0) {
            node.right = putHelper(node.right, key, value);
        } else {
            node.value = value;
        }
        return node;
    }

    /* Returns a set of all keys in the map in sorted order. */
    @Override
    public Set<K> keySet() {
        Set<K> keys = new TreeSet<>();
        collectKeys(root, keys);
        return keys;
    }

    private void collectKeys(BSTNode node, Set<K> keys) {
        if (node == null) {
            return;
        }
        collectKeys(node.left, keys);
        keys.add(node.key);
        collectKeys(node.right, keys);
    }

    /* Removes the node with the specified key from the map. */
    @Override
    public V remove(K key) {
        V value = get(key);
        if (value != null) {
            root = removeHelper(root, key);
            size -= 1;
        }
        return value;
    }

    private BSTNode removeHelper(BSTNode node, K key) {
        if (node == null) {
            return null;
        }
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = removeHelper(node.left, key);
        } else if (cmp > 0) {
            node.right = removeHelper(node.right, key);
        } else {
            if (node.left == null) {
                return node.right; // Replace with right subtree
            } else if (node.right == null) {
                return node.left;  // Replace with left subtree
            }
            BSTNode current = node;
            node = findMin(current.right);
            node.right = deleteMin(current.right);
            node.left = current.left;
        }
        return node;
    }

    private BSTNode findMin(BSTNode node) {
        if (node.left == null) {
            return node;
        } else {
            return findMin(node.left);
        }
    }

    private BSTNode deleteMin(BSTNode node) {
        if (node.left == null) {
            return node.right;
        }
        node.left = deleteMin(node.left);
        return node;
    }


    /* Removes the node with the specified key only if it maps to the specified value. */
    @Override
    public V remove(K key, V value) {
        V currentValue = get(key);
        if (currentValue != null && currentValue.equals(value)) {
            root = removeHelper(root, key);
            size -= 1;
            return currentValue;
        }
        return null;
    }

    /* Returns an iterator over the keys in the map in sorted order. */
    @Override
    public Iterator<K> iterator() {
        return keySet().iterator();
    }


    /* Prints the BSTMap in order of increasing keys (in-order traversal). */
    public void printInOrder() {
        printInOrderHelper(root);
    }

    private void printInOrderHelper(BSTNode node) {
        if (node == null) {
            return;
        }
        printInOrderHelper(node.left);
        StdOut.println(node.key);
        printInOrderHelper(node.right);
    }

}
