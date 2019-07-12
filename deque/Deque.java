
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private class Node {
        private Item item;
        private Node previous;
        private Node next;
    }

    private Node first;
    private Node last;
    private int n;

    // construct an empty deque
    public Deque() {}

    // is the deque empty?
    public boolean isEmpty() {
        return n == 0;
    }

    // return the number of items on the deque
    public int size() {
        return n;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }

        Node newNode = new Node();
        newNode.item = item;

        if (isEmpty()) {
            first = newNode;
            last = newNode;
        } else {
            newNode.previous = null;
            newNode.next = first;
            first.previous = newNode;
            first = newNode;
        }
        n++;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }

        Node newNode = new Node();
        newNode.item = item;

        if (isEmpty()) {
            first = newNode;
            last = newNode;
        } else {
            newNode.previous = last;
            newNode.next = null;
            last.next = newNode;
            last = newNode;
        }
        n++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (first == null) {
            throw new NoSuchElementException("Can not remove an item from empty deque.");
        }

        Item item = first.item;
        first = first.next;
        if (first == null) {
            last = null;
        } else {
            first.previous = null;
        }
        n--;
        return item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (first == null) {
            throw new NoSuchElementException("Can not remove an Item from empty deque.");
        }

        Item item = last.item;
        last = last.previous;
        if (last == null) {
            first = null;
        } else {
            last.next = null;
        }
        n--;
        return item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }


    private class DequeIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException("remove is not supported");
        }

        public Item next() {
            if (current == null) {
                throw new NoSuchElementException();
            }
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    // unit testing (required)
    public static void main(String[] args) {

    }

}
