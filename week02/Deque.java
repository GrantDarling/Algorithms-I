import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {
    private Node first;
    private Node last;
    private int size;

    private class Node<Item> {
        private Item item;
        private Node prev;
        private Node next;
    }

    // may not be needed
    private Item Item;
    Iterator<Item> iterator;
    // end may not be needed

    // construct an empty deque
    public Deque() {
        first = null;
        last = null;
        size = 0;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return true;
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (size == 0) {
            first = new Node();
						// ...
						last = first;
        } else {
            // ...
        }
				
				size++;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (last == null) {
            last = new Node();
            // ...
						first = last;
        } else {
            // ...
        }
        size++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        return Item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        return Item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return iterator;
    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<String> deque1 = new Deque<String>();
        deque1.addFirst("1");
        deque1.addFirst("hey");
        deque1.addFirst("17");

        deque1.addLast("66");
        deque1.addLast("77");
        deque1.addLast("88");

        System.out.printf("Size is %d \n", deque1.size());
        System.out.printf("first is %s \n", deque1.first);
        System.out.printf("first is %s \n", deque1.last);
    }
}
