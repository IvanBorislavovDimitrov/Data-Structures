import java.util.ArrayList;
import java.util.List;

public class BinaryHeap<T extends Comparable<T>> {

    private static final String HEAP_IS_EMPTY = "Heap is empty!";

    private List<T> heap;
    private int size;

    public BinaryHeap() {
        this.heap = new ArrayList<>();
    }

    public int size() {
        return this.size;
    }

    public void insert(T element) {
        this.heap.add(element);
        this.heapifyUp(this.heap.size() - 1);
        this.size++;
    }

    private void heapifyUp(int child) {
        while (child > 0 && isParentSmaller(child, (child - 1) / 2)) {
            this.swap(child, (child - 1) / 2);
            child = (child - 1) / 2;
        }
    }

    private boolean isParentSmaller(int child, int parent) {
        int compare = this.heap.get(parent).compareTo(this.heap.get(child));
        return compare < 0;
    }

    private void swap(int child, int parent) {
        T currentElement = this.heap.get(child);
        this.heap.set(child, this.heap.get(parent));
        this.heap.set(parent, currentElement);
    }

    public T peek() {
        return this.heap.get(0);
    }

    public T pull() {
        if (this.size == 0) {
            throw new IllegalArgumentException(HEAP_IS_EMPTY);
        }
        this.size--;

        T item = this.heap.get(0);
        this.swap(0, this.heap.size() - 1);
        this.heap.remove(this.heap.size() - 1);
        this.heapifyDown(0);

        return item;
    }

    private void heapifyDown(int parent) {
        while (parent < this.heap.size() / 2) {
            int child = 2 * parent + 1;
            if (hasRightChild(child + 1) && isParentSmaller(child, child + 1)) {
                child++;
            }

            if (!isParentSmaller(child, parent)) {
                break;
            }

            this.swap(parent, child);
            parent = child;
        }
    }

    private boolean hasRightChild(int child) {
        return child < this.heap.size() - 1;
    }
}
