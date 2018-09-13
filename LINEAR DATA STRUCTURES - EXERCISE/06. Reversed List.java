import java.util.*;

public class ReversedList<T> implements Iterable<T> {

    private Object[] elements;
    private int count;
    private static final int INITIAL_SIZE = 2;


    public ReversedList() {
        this.elements = new Object[INITIAL_SIZE];
        this.count = 0;
    }

    public void add(T item) {
        if (this.count == this.elements.length) {
            this.grow();
        }

        this.elements[this.count] = item;
        this.count++;
    }

    public int count() {
        return this.count;
    }

    public int capacity() {
        return this.elements.length;
    }

    public T get(int index) {
        if (index < 0 || index > this.count) {
            throw new IllegalArgumentException();
        }

        return (T) this.elements[this.count - 1 - index];
    }

    public T removeAt(int index) {
        if (index < 0 || index > this.count) {
            throw new IllegalArgumentException();
        }
        Object element = this.elements[this.count - 1 - index];
        this.shiftRight(this.count - 1 - index);
        this.elements[this.count] = null;
        this.count--;

        return (T) element;
    }

    public void set(int index, T element) {
        if (index < 0 || index > this.count) {
            throw new IllegalArgumentException();
        }

        this.elements[index] = element;
    }

    private void shiftRight(int index) {
        for (int i = index; i < this.count; i++) {
            Object swap = this.elements[i];
            this.elements[i] = this.elements[i + 1];
            this.elements[i + 1] = swap;
        }
    }

    private void grow() {
        Object[] newElements = new Object[this.elements.length * 2];
        int cnt = 0;
        for (Object o : this.elements) {
            newElements[cnt++] = o;
        }
        this.elements = newElements;
    }

    @Override
    public Iterator<T> iterator() {
        List<T> list = new ArrayList<>();
        for (int i = 0; i < this.count; i++) {
            list.add((T) this.elements[i]);
        }
        Collections.reverse(list);

        return list.iterator();
    }
}
