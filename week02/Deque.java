import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private Node<Item> first;
    private Node<Item> last;
    private int size;

    private class Node<Item> {
        private Item item;
        private Deque<Item>.Node<Item> prev;
        private Deque<Item>.Node<Item> next;
    }

    // construct an empty deque
    public Deque() {
        first = null;
        last = null;
        size = 0;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (size == 0) {
            first = new Node<Item>();
            first.item = item;
            first.next = null;
            first.prev = null;
            last = first;
        } else {
            Node<Item> firstOld = first;
            first = new Node<Item>();
            first.item = item;
            first.next = firstOld;
            first.prev = null;
            firstOld.prev = first;
        }

        size++;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (size == 0) {
            last = new Node<>();
            last.item = item;
            last.prev = null;
            last.next = null;
            first = last;
        } else {
            Node<Item> lastOld = last;
            last = new Node<>();
            last.item = item;
            last.next = null;
            last.prev = lastOld;
            lastOld.next = last;
        }
        size++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty())
            throw new NoSuchElementException("Deque underflow");
        Item item = first.item; // item to be removed
        if (size > 1) {
            first = first.next;
            first.prev = null;
        } else {
            first = null;
            last = first;
        }
        size--;
        return item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty())
            throw new NoSuchElementException("Deque underflow");
        Item item = last.item;
        if (size > 1) {
            last = last.prev;
            last.next = null;
        } else {
            last = null;
            first = last;
        }
        size--;
        return item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {
        private Node<Item> current = first;

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext())
                throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
    }
}
