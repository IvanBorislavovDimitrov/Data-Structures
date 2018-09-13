public class Main {

    public static void main(String[] args) {
        BinaryHeap<Integer> heap = new BinaryHeap<>();

        heap.insert(3);
        heap.insert(5);
        heap.insert(6);
        heap.insert(7);

        Integer[] arr = {4, 3, 2, 1};
        Heap.sort(arr);
        System.out.println();
    }
}
