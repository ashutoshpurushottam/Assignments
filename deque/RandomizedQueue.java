import edu.princeton.cs.algs4.StdRandom;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] q;
    private int n;
    private int first; // index of first element of queue
    private int last; // index of next available slot

    // construct an empty randomized queue
    public RandomizedQueue() {
        q = (Item[]) new Object[2];
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return n == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return n;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }
        if (n == q.length) resize(2 * q.length);
        q[last++] = item;
        if (last == q.length) last = 0;
        n++;
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty())
            throw new NoSuchElementException("Queue underflow");
        int index = StdRandom.uniform(n);

        Item item = q[index];
        if (index == first) { // remove the first item
            first++;
            if (first == q.length)
                first = 0; // wrap-around
            // shrink size of array if necessary
            if (n > 0 && n == q.length / 4)
                resize(q.length / 2);
        } else if (index == last - 1) { // remove the last item
            if (last == 0) {
                last = n-1;
            } else {
                last--;
            }
        } else {
            for (int i = index; i < n - 1; ++i) { // remove the middle item
                q[i] = q[i + 1];
            }
            if (last == 0) {
                last = n-1;
            } else {
                last--;
            }
        }
        n--;
        if (n == 0) {
            first = 0;
            last = 0;
        }
        return item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty())
            throw new NoSuchElementException("underflow");
        return q[StdRandom.uniform(n)];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new ArrayIterator();
    }

    private void resize(int capacity) {
        assert capacity >= n;
        Item[] temp = (Item[]) new Object[capacity];
        for (int i = 0; i < n; i++) {
            temp[i] = q[(first + i) % q.length];
        }
        q = temp;
        first = 0;
        last = n;
    }

    // an iterator, doesn't implement remove() since it's optional
    private class ArrayIterator implements Iterator<Item> {
        private int i = 0;
        private int[] perm;

        public ArrayIterator() {
            perm = new int[n];
            for (int arrI = 0; arrI < n; ++arrI) {
                perm[arrI] = arrI;
            }
            StdRandom.shuffle(perm);
        }

        public boolean hasNext() {
            return i < n;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext())
                throw new NoSuchElementException();
            Item item = q[perm[i]];
            i++;
            return item;
        }
    }



    // unit testing (required)
    public static void main(String[] args) {

    }
}
