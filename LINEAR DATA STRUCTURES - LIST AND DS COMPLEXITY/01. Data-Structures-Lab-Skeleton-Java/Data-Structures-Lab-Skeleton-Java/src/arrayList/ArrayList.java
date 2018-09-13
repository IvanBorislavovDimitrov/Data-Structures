package arrayList;

public class ArrayList<T> {

    private Object[] elements;
    private static final int INITIAL_SIZE = 4;
    private int length;

    public ArrayList(int capacity) {
        this.elements = new Object[capacity];
    }

    public ArrayList() {
        this(INITIAL_SIZE);
    }


    public int getCount() {
        return this.length;
    }


    public T get(int index) {
        if (index < 0 || index >= this.length) {
            throw new IndexOutOfBoundsException();
        }

        return (T) this.elements[index];
    }


    public void add(T element) {
        if (this.length == this.elements.length) {
            Object[] oldElements = this.elements;
            this.elements = new Object[this.length * 2];
            System.arraycopy(oldElements, 0, this.elements, 0, oldElements.length);
        }
        this.elements[this.length] = element;
        this.length++;
    }

    public T removeAt(int index) {
        if (index < 0 || index >= this.length) {
            throw new IndexOutOfBoundsException();
        }

        Object element = this.elements[index];
        this.elements[index] = null;
        for (int i = index; i < this.length - 1; i++) {
            Object swap = this.elements[i];
            this.elements[i] = this.elements[i + 1];
            this.elements[i + 1] = swap;
        }
        this.length--;

        return (T) element;
    }


    public void set(int i, T item) {
        if (i < 0 || i >= this.length) {
            throw new IndexOutOfBoundsException();
        }

        this.elements[i] = item;
    }

}
