import java.util.Iterator;

public class LinkedList<E> implements Iterable<E> {

    private int size;

    private class Node {
        private E element;
        private Node next;
        private Node prev;

        private Node() {}

        private Node(E element) {
            this.element = element;
        }
    }

    private Node head;
    private Node tail;

    public LinkedList() { }

    public LinkedList(E element) {
        this.head = new Node(element);
    }

    public int size() {
        return this.size;
    }

    private void setSize(int size) {
        this.size = size;
    }

    public void addFirst(E item) {
        if (this.head == null) {
            this.head = new Node(item);
            this.tail = this.head;
        } else {
            this.head.prev = new Node(item);
            Node currentElement = this.head;
            this.head = this.head.prev;
            this.head.next = currentElement;
        }
        this.size++;
    }

    public void addLast(E item) {
        if (this.head == null) {
            this.head = new Node(item);
            this.tail = this.head;
        } else {
            Node prev = this.tail;
            this.tail = new Node(item);
            prev.next = this.tail;
            this.tail.prev = prev;
        }

        this.size++;
    }

    public E removeFirst() {
        Node currentHead = this.head;
        this.head = this.head.next;
        this.size--;

        return currentHead.element;
    }

    public E removeLast() {
        Node currentTail = this.tail;
        this.tail = this.tail.prev;
        if (this.tail != null) {
            this.tail.next = null;
        }
        this.size--;
        if (this.size == 0) {
            this.head = null;
            this.tail = null;
        }

        return currentTail.element;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private Node current = head;
            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public E next() {
                Node nowCurrent = this.current;
                this.current = this.current.next;
                return nowCurrent.element;
            }
        };
    }

}
