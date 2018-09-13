import java.lang.annotation.ElementType;
import java.util.Arrays;
import java.util.Iterator;
import java.util.function.Consumer;

public class CircularQueue<E> implements Iterable<E> {

    private int size;
    private Object[] elements;
    private static final int INITIAL_SIZE = 4;
    private int start;
    private int end;


    public CircularQueue() {
        this(INITIAL_SIZE);
    }

    public CircularQueue(int initialCapacity) {
        this.elements = new Object[initialCapacity];
    }

    public int size() {
        return this.size;
    }

    private void setSize(int size) {
        this.size = size;
    }

    public void enqueue(E element) {
        if (this.size == elements.length) {
            Object[] tempElements = new Object[this.size * 2];
            System.arraycopy(this.elements, 0, tempElements, 0, this.elements.length);
            this.elements = tempElements;
            this.start = 0;
            this.end = this.size;
        }
        this.elements[this.end] = element;
        this.end = (this.end + 1) % this.elements.length;
        this.size++;
    }

    public E dequeue() {
        if (this.size == 0) {
            throw new IllegalArgumentException();
        }

        E element = (E) this.elements[this.start];
        this.size--;
        this.start = (this.start + 1) % this.elements.length;

        return element;
    }

    public Object[] toArray() {
        Object[] arr = new Object[this.size];
        int start = this.start;
        for (int i = 0; i < this.size; i++) {
            arr[i] = (E) this.elements[start];
            start = (start + 1) % this.elements.length;
        }

        return arr;
    }

    public int getSize() {
        return this.size;
    }

    public Object[] getElements() {
        return this.elements;
    }

    public static int getInitialSize() {
        return INITIAL_SIZE;
    }

    public int getStart() {
        return this.start;
    }

    public int getEnd() {
        return this.end;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int start = getStart();
            private int size = getSize();
            private int startIndex = 0;
            @Override
            public boolean hasNext() {
                return this.startIndex < this.size;
            }

            @Override
            public E next() {
                E element = (E) elements[start];
                this.start = (this.start + 1) % elements.length;
                this.startIndex++;

                return element;
            }
        };
    }

    @Override
    public void forEach(Consumer<? super E> action) {
        for (Object element : this.toArray()) {
            action.accept((E) element);
        }
    }
}
