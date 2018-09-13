public class Main {

    public static void main(String[] args) {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>(1);

        tree.insert(3);
        tree.insert(4);
        tree.insert(5);
        tree.insert(8);
        tree.insert(9);
        tree.insert(10);
        tree.insert(37);
        tree.insert(39);
        tree.insert(45);

        tree.delete(4);

        System.out.println();
    }
}
